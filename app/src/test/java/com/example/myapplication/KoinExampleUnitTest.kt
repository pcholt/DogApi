package com.example.myapplication

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jraska.livedata.test
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.doThrow
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Test
import org.junit.Before
import org.junit.Rule
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.test.AutoCloseKoinTest
import org.koin.test.inject
import java.lang.Exception
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

/**
 * Unit testing using
 *   - Koin to construct a test system of modules and mocks of dependencies
 *   - nhaarman's MockingKt to decorate the mocks in kotlinesque fashion
 *   - Dispatchers.setMain() to add a single threading executor for testing
 *   - jraska's LiveData test assertions
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */

const val CAT_URL = "http://localhost/cat.jpg"

class KoinExampleUnitTest : AutoCloseKoinTest() {

    private val viewModel: MainViewModel by inject()

    @get:Rule
    var rule = InstantTaskExecutorRule()

    private val mainThreadSurrogate = Executors.newSingleThreadExecutor().asCoroutineDispatcher()

    @ExperimentalCoroutinesApi
    @Before
    fun before() {
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @ExperimentalCoroutinesApi
    @After
    fun after() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

    @Test
    fun `viewmodel performs as expected`() {

        startKoin {
            modules(listOf(viewModelModule, module {
                single {
                    mock<AnimalSearchManager> {
                        onBlocking { search() } doReturn CAT_URL
                    }
                }
            }))
        }

        runBlocking {
            viewModel.fetch()
            viewModel.animal
                    .test()
                    .awaitValue(5, TimeUnit.SECONDS)
                    .assertValue(CAT_URL)
        }

    }

    @Test
    fun `viewmodel fails`() {

        startKoin {
            modules(listOf(viewModelModule, module {
                single {
                    mock<AnimalSearchManager> {
                        onBlocking { search() } doThrow Exception()
                    }
                }
            }))
        }

        runBlocking {
            viewModel.fetch()
            viewModel.error
                    .test()
                    .awaitValue(2, TimeUnit.SECONDS)
                    .assertHasValue()
            viewModel.animal
                    .test()
                    .assertNoValue()
        }

    }
}


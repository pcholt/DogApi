package com.example.myapplication

import org.junit.Test

import org.junit.Assert.assertEquals
import org.junit.Before
import org.koin.core.context.startKoin
import org.koin.test.AutoCloseKoinTest

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
class KoinExampleUnitTest : AutoCloseKoinTest() {

    @Before
    fun before() {
        startKoin {
            modules(listOf(viewmodelModule, catNetworkEndpoint1))
        }
    }
    @Test
    fun addition_isCorrect() {
        assertEquals(4, (2 + 2).toLong())
    }
}
package com.example.myapplication

import android.content.Context

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.core.app.ApplicationProvider
import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.assertEquals
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.koinApplication

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
@RunWith(AndroidJUnit4::class)
class KoinInstrumentedTest {

    @Test
    fun useAppContext() {

        // Context of the app under test.
        val appContext = ApplicationProvider.getApplicationContext<Context>()

        stopKoin()

        startKoin {
            androidContext(appContext)
            modules(listOf(viewModelModule, catNetworkEndpoint1))
        }

        koinApplication {
            assertEquals("com.example.myapplication", appContext.packageName)
        }

    }
}

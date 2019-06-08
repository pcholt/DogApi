package com.example.myapplication

import android.app.Application
import com.example.myapplication.net.RandomDog
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SimpleApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@SimpleApplication)
            modules(listOf(viewmodelModule, networkModule))
        }
    }
}

val networkModule = module {
    single(named("dogs")) {
        Retrofit.Builder()
                .baseUrl("https://random.dog/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }
    single(named("cats")) {
        Retrofit.Builder()
                .baseUrl("https://aws.random.cat/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }
    single(named("thecatapi")) {
        Retrofit.Builder()
                .baseUrl("https://api.thecatapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }
    single {
        get<Retrofit>(named("dogs")).create(RandomDog::class.java)
        get<Retrofit>(named("cats")).create(RandomCat::class.java)
        get<Retrofit>(named("thecatapi")).create(TheCatApi::class.java)
    }
}

val viewmodelModule = module {
    viewModel {
        MainViewModel(get())
    }
}
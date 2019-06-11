package com.example.myapplication

import android.app.Application
import com.example.myapplication.manager.CatApiSearchManager
import com.example.myapplication.manager.RandomCatSearchManager
import com.example.myapplication.manager.RandomDogSearchManager
import com.example.myapplication.net.RandomCat
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
            modules(listOf(viewModelModule, dogNetworkEndpoint))
        }
    }
}

val dogNetworkEndpoint = module {
    single { retrofit("https://random.dog/") }
    single { get<Retrofit>().create(RandomDog::class.java) }
    single<AnimalSearchManager> { RandomDogSearchManager(get()) }
}

val catNetworkEndpoint1 = module {
    single(named("cats")) { retrofit("https://aws.random.cat/") }
    single {
        get<Retrofit>(named("cats")).create(RandomCat::class.java)
    }
    single<AnimalSearchManager> { RandomCatSearchManager(get()) }
}

val catNetworkEndpoint2 = module {
    single(named("thecatapi")) { retrofit("https://api.thecatapi.com/") }
    single { get<Retrofit>(named("thecatapi")).create(TheCatApi::class.java)}
    single<AnimalSearchManager> { CatApiSearchManager(get()) }
}

private fun retrofit(baseUrl: String): Retrofit {
    return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
}

val viewModelModule = module {
    viewModel {
        MainViewModel(get())
    }
}
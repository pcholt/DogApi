package com.example.myapplication.net

import com.example.myapplication.AnimalImageProvider
import retrofit2.http.GET

interface RandomDog {
    @GET("woof.json")
    suspend fun randomDog() : Dog
}

data class Dog(
        val url: String
) : AnimalImageProvider {
    override val imageUrl: String
        get() = url
}
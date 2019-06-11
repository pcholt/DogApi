package com.example.myapplication.net

import retrofit2.http.GET

interface RandomDog {
    @GET("woof.json")
    suspend fun randomDog() : Dog
}

data class Dog(
        val url: String
)
package com.example.myapplication

import retrofit2.http.GET

interface RandomCat {
    @GET("meow")
    suspend fun randomCat() : Cat
}

data class Cat(
        val file: String
)
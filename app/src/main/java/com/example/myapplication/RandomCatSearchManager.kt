package com.example.myapplication

import retrofit2.Retrofit

class RandomCatSearchManager(val randomCat: RandomCat) : AnimalSearchManager {
    override suspend fun search() =
        randomCat.randomCat().file

}

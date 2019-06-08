package com.example.myapplication

import com.example.myapplication.net.RandomDog

class RandomDogSearchManager(val get: RandomDog) : AnimalSearchManager {
    override suspend fun search() = get.randomDog().url
}

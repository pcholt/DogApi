package com.example.myapplication.manager

import com.example.myapplication.AnimalSearchManager
import com.example.myapplication.net.RandomDog

class RandomDogSearchManager(val get: RandomDog) : AnimalSearchManager {
    override suspend fun search() : String {
        var url = get.randomDog().url
        while (url.endsWith(".mpg")) {
            url = get.randomDog().url
        }
        return url
    }
}

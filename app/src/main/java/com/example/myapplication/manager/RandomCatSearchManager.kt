package com.example.myapplication.manager

import com.example.myapplication.AnimalSearchManager
import com.example.myapplication.net.RandomCat

class RandomCatSearchManager(private val randomCat: RandomCat) : AnimalSearchManager {
    override suspend fun search() =
        randomCat.randomCat().file

}

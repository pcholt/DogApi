package com.example.myapplication.manager

import com.example.myapplication.AnimalSearchManager
import com.example.myapplication.TheCatApi

class CatApiSearchManager(private val api: TheCatApi) : AnimalSearchManager {
    override suspend fun search() = api.search().firstOrNull()?.url
}

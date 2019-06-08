package com.example.myapplication

class CatApiSearchManager(private val api: TheCatApi) : AnimalSearchManager {
    override suspend fun search() = api.search().firstOrNull()?.url
}

package com.example.myapplication

import retrofit2.http.GET

interface TheCatApi {
    @GET ("v1/images/search")
    suspend fun search() : Array<CatApiCat>
}

data class CatApiCat(val breeds : Array<String>, val id:String, val url:String) : AnimalImageProvider {
    override val imageUrl get() = url

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CatApiCat

        if (!breeds.contentEquals(other.breeds)) return false
        if (id != other.id) return false
        if (url != other.url) return false

        return true
    }

    override fun hashCode(): Int {
        var result = breeds.contentHashCode()
        result = 31 * result + id.hashCode()
        result = 31 * result + url.hashCode()
        return result
    }
}

interface AnimalImageProvider {
    val imageUrl : String
}
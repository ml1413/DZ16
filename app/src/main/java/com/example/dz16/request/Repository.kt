package com.example.dz16.request

import com.example.dz16.models.Character
import retrofit2.Response

class Repository(val client: ApiClient) {
    val apiInterface = client.retrofit.create(ApiInterface::class.java)
    suspend fun getAllHero(): List<Character> {
        return apiInterface.getAllHero()
    }
    suspend fun getHeroFromId(id: String): Response<Character> {
       return apiInterface.getHeroFromId(id = id)
    }
}
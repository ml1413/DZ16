package com.example.dz16.request

import com.example.dz16.models.Character
import retrofit2.Retrofit

class Repository(val client: Retrofit) {
    val apiInterface = client.create(ApiInterface::class.java)
    suspend fun getAllHero(): List<Character> {
        return apiInterface.getAllHero()
    }
}
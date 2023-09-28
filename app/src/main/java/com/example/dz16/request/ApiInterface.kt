package com.example.dz16.request

import com.example.dz16.models.Character
import retrofit2.http.GET

interface ApiInterface {
    @GET("superhero-api/api/all.json")
    suspend fun getAllHero(): List<Character>
}
package com.example.dz16.request

import com.example.dz16.models.Character
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {
    @GET("superhero-api/api/all.json")
    suspend fun getAllHero(): List<Character>

    @GET("superhero-api/api/id/{id}.json")
    suspend fun getHeroFromId(
        @Path("id") id: String
    ): Response<Character>
}
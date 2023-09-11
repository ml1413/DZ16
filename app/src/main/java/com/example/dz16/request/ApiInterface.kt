package com.example.dz16.request

import com.example.dz16.models.Character
import io.reactivex.Single
import retrofit2.http.GET

interface ApiInterface {
    @GET("superhero-api/api/all.json")
    fun getAllHero(): Single<List<Character>>
}
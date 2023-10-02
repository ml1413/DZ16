package com.example.dz16.request

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class ApiClient @Inject constructor() {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://akabab.github.io")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}
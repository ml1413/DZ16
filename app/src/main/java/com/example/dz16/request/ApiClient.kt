package com.example.dz16.request


import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private val clientOkHttp = OkHttpClient.Builder()
//        .addInterceptor(getInterceptor())
        .build()

    private fun getInterceptor(): HttpLoggingInterceptor {
        val inter = HttpLoggingInterceptor()
        inter.level = HttpLoggingInterceptor.Level.BODY
        return inter
    }

    val retrofit: Retrofit = Retrofit.Builder()
        .client(clientOkHttp)
        .baseUrl("https://akabab.github.io")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()


}
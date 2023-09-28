package com.example.dz16

import android.app.Application
import com.example.dz16.request.Repository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class App : Application() {
    lateinit var repository: Repository
    override fun onCreate() {
        super.onCreate()
        instance = this
        repository = Repository(client = getApiClient())
    }

    private fun getApiClient(): Retrofit {
        val interceptor =
            HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
        //_________________________________________________________________________________________
        val client = OkHttpClient.Builder()
            //.addInterceptor(interceptor)
            .build()
        //_________________________________________________________________________________________
        return Retrofit.Builder()
            .client(client)
            .baseUrl("https://akabab.github.io")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    companion object {
        private lateinit var instance: App
        fun getApp() = instance
    }
}
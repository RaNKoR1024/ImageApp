package com.example.imageapp

import android.app.Application
import com.example.imageapp.data.remote.serp.SerpApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ImageApp : Application() {

    lateinit var serpApi: SerpApi

    override fun onCreate() {
        super.onCreate()

        configureRetrofit()
    }

    private fun configureRetrofit() {

        val retrofit = Retrofit.Builder()
            .baseUrl("https://serpapi.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        serpApi = retrofit.create(SerpApi::class.java)

    }
}
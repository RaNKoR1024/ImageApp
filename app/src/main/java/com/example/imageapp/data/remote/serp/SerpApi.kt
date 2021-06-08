package com.example.imageapp.data.remote.serp

import com.example.imageapp.data.remote.ApiKeys
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface SerpApi {

    @GET("/search.json")
    fun getImagesList(@Query("q") query: String,
                      @Query("tbm") tbm: String = "isch",
                      @Query("api_key") apiKey: String = ApiKeys.SERP_API_KEY,
    ) : Call<ImageListResponse>

}
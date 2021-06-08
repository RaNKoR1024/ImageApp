package com.example.imageapp.data.remote.serp

import com.google.gson.annotations.SerializedName

data class ImageListResponse(
    @SerializedName("images_result")
    val images: List<SerpImage>
)

data class SerpImage(
    val position: Int,
    val thumbnail: String,
    val original: String,
    val source: String,
    val title: String,
    val link: String
)
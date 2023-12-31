package com.example.customview.volumeone.net

import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Streaming
import retrofit2.http.Url

interface Service {

    @GET
    @Streaming
    suspend fun downloadImage(@Url imageUrl: String): ResponseBody
}
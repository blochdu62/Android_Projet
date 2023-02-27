package com.example.android_projet

import okhttp3.ResponseBody
import retrofit2.http.GET


interface ApiService {
    @GET("countries/flag/images")
    suspend fun getFlags(): ResponseBody
}

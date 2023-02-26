package com.example.android_projet

import com.google.gson.JsonObject
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET


interface ApiService {
    @GET("countries/flag/images")
    suspend fun getFlags(): ResponseBody
}

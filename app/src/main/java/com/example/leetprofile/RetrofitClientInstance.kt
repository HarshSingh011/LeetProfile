package com.example.leetprofile

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClientInstance {
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(Utility.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: LeetProfile_Interface by lazy {
        retrofit.create(LeetProfile_Interface::class.java)
    }
}

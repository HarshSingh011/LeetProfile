package com.example.leetprofile

import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.GET
import retrofit2.http.Path

interface LeetProfile_Interface {
    @GET("{username}/solved")
    fun getSolvedProblems(@Path("username") username: String): Call<List<UserDetails>>
}

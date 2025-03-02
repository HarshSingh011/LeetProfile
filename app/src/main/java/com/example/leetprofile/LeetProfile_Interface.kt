package com.example.leetprofile

import com.example.leetprofile.Dataclasses.BadgesResponse
import com.example.leetprofile.Dataclasses.account
import com.example.leetprofile.Dataclasses.solved
import retrofit2.http.GET
import retrofit2.http.Path

interface LeetProfile_Interface {
    @GET("{username}/badges")
    suspend fun getBadges(@Path("username") username: String): BadgesResponse

    @GET("{username}/solved")
    suspend fun getProblemStats(@Path("username") username: String): solved

    @GET("{username}")
    suspend fun getUserProfile(@Path("username") username: String): account

    @GET("{username}/submission")
    suspend fun getSubmission(@Path("username") username: String): SubmissionResponse

}


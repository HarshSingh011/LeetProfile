package com.example.leetprofile

import com.example.leetprofile.Dataclasses.BadgesResponse
import com.example.leetprofile.Dataclasses.ProblemStatsResponse
import com.example.leetprofile.Dataclasses.SubmissionResponse
import com.example.leetprofile.Dataclasses.account
import retrofit2.http.GET
import retrofit2.http.Path

interface LeetProfile_Interface {
    @GET("{username}/badges")
    suspend fun getBadges(@Path("username") username: String): BadgesResponse

    @GET("{username}/solved")
    suspend fun getProblemStats(@Path("username") username: String): ProblemStatsResponse

    @GET("{username}")
    suspend fun getUserProfile(@Path("username") username: String): account

    @GET("{username}/submission")
    suspend fun getSubmission(@Path("username") username: String): SubmissionResponse
}
package com.example.leetprofile.Repository

import com.example.leetprofile.Dataclasses.BadgesResponse
import com.example.leetprofile.Dataclasses.ProblemStatsResponse
import com.example.leetprofile.Dataclasses.SubmissionResponse
import com.example.leetprofile.Dataclasses.account
import com.example.leetprofile.RetrofitClientInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepository {
    private val api = RetrofitClientInstance.api

    private var cachedProfile: account? = null
    private var cachedUsername: String? = null
    private var cachedProblemStats: ProblemStatsResponse? = null
    private var cachedSubmissions: SubmissionResponse? = null

    suspend fun getUserBadges(username: String): BadgesResponse {
        return RetrofitClientInstance.api.getBadges(username)
    }

    suspend fun getUserProfile(username: String): account {
        if (username == cachedUsername && cachedProfile != null) {
            return cachedProfile!!
        }

        return withContext(Dispatchers.IO) {
            try {
                val profile = api.getUserProfile(username)
                cachedProfile = profile
                cachedUsername = username
                profile
            } catch (e: Exception) {
                throw e
            }
        }
    }

    suspend fun getProblemStats(username: String): ProblemStatsResponse {
        if (username == cachedUsername && cachedProblemStats != null) {
            return cachedProblemStats!!
        }

        return withContext(Dispatchers.IO) {
            try {
                val stats = api.getProblemStats(username)
                cachedProblemStats = stats
                cachedUsername = username
                stats
            } catch (e: Exception) {
                throw e
            }
        }
    }

    suspend fun getSubmissions(username: String): SubmissionResponse {
        if (username == cachedUsername && cachedSubmissions != null) {
            return cachedSubmissions!!
        }

        return withContext(Dispatchers.IO) {
            try {
                val submissions = api.getSubmission(username)
                cachedSubmissions = submissions
                cachedUsername = username
                submissions
            } catch (e: Exception) {
                throw e
            }
        }
    }

    fun clearCache() {
        cachedProfile = null
        cachedUsername = null
        cachedProblemStats = null
        cachedSubmissions = null
    }
}
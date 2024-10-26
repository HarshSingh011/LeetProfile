package com.example.leetprofile

data class UserDetails(
    var username: String,
    val acSubmission: String,
    val badges: String,
    val calendar: String,
    val contest: String,
    val contestHistory: String,
    val solved: String,
    val submission: String,
    val languageStats: String,
    val skillStats: String,
    val userProfile: String,
    val userProfileCalendar: String,
    val userProfileUserQuestionProgress: String,
    val method: String,
    val description: String
) {
    fun getEndpoints(): Map<String, String> {
        return mapOf(
            "username" to "/$username",
            "acSubmission" to "/$username/acSubmission",
            "badges" to "/$username/badges",
            "calendar" to "/$username/calendar",
            "contest" to "/$username/contest",
            "contestHistory" to "/$username/contest/history",
            "solved" to "/$username/solved",
            "submission" to "/$username/submission",
            "languageStats" to "/languageStats?username=$username",
            "skillStats" to "/skillStats/$username",
            "userProfile" to "/userProfile/$username",
            "userProfileCalendar" to "/userProfileCalendar?username=$username&year=2024",
            "userProfileUserQuestionProgress" to "/userProfileUserQuestionProgressV2/$username",
            "method" to method,
            "description" to description
        )
    }
}

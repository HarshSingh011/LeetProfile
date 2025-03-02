package com.example.leetprofile.Dataclasses

data class BadgesResponse(
    val activeBadge: Bgs,
    val badges: List<Bgs>,
    val badgesCount: Int,
    val upcomingBadges: List<UpcomingBadge>
)

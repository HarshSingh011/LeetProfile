package com.example.leetprofile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.google.gson.Gson // Make sure to import Gson
import kotlinx.coroutines.launch

class BadgeFragment : Fragment() {
    private lateinit var badgesUser: TextView
    private lateinit var badgesCount: TextView
    private lateinit var upcomingBadges: TextView
    private lateinit var activeBadge: TextView

    private val userViewModel: UserViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_badge, container, false)
        badgesUser = view.findViewById(R.id.badgesUser)
        badgesCount = view.findViewById(R.id.badgesCount)
        upcomingBadges = view.findViewById(R.id.upcomingBadges)
        activeBadge = view.findViewById(R.id.activeBadge)

        userViewModel.username.observe(viewLifecycleOwner) { username ->
            apiCall(username)
        }

        return view
    }

    private fun apiCall(userName: String) {
        lifecycleScope.launch {
            try {
                val badgesResponse: BadgesResponse = RetrofitClientInstance.api.getBadges(userName)

                val badgesText = badgesResponse.badges.takeIf { it.isNotEmpty() }?.joinToString(", ") { it.displayName }
                    ?: "No badges available"
                badgesUser.text = "Badges: $badgesText"

                badgesCount.text = "Badges Count: ${badgesResponse.badges.size}"

                val upcomingText = badgesResponse.upcomingBadges.takeIf { it.isNotEmpty() }?.joinToString(", ") { it.name }
                    ?: "No upcoming badges"
                upcomingBadges.text = "Upcoming Badges: $upcomingText"

                activeBadge.text = "Active Badge: ${badgesResponse.activeBadge?.displayName ?: "NULL"}"

            } catch (e: Exception) {
                println("Error: $e")
                badgesUser.text = "An error occurred: ${e.message}"
            }
        }
    }
}

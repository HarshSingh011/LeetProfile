package com.example.leetprofile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.net.HttpRetryException

class BadgeFragment : Fragment() {
    private lateinit var badgesUser: TextView
    private lateinit var badgesCount: TextView
    private lateinit var upcomingBadges: TextView
    private lateinit var activeBadge: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_badge, container, false)
        badgesUser = view.findViewById(R.id.badgesUser)
        badgesCount = view.findViewById(R.id.badgesCount)
        upcomingBadges = view.findViewById(R.id.upcomingBadges)
        activeBadge = view.findViewById(R.id.activeBadge)

        apiCall("harshroot12")

        return view
    }

    private fun apiCall(userName: String) {
        lifecycleScope.launch {
            try {
                val badgesResponse = RetrofitClientInstance.api.getBadges(userName)

                println("Badges Response: $badgesResponse")

                badgesUser.text = "Badges: ${badgesResponse.badges.joinToString(", ") { it.displayName }}"
                badgesCount.text = "Badges Count: ${badgesResponse.badgesCount}"
                upcomingBadges.text = "Upcoming Badges: ${badgesResponse.upcomingBadges.joinToString(", ") { it.name }}"
                activeBadge.text = "Active Badge: ${badgesResponse.activeBadge.displayName}"

            }catch (e: Exception) {
                println("Error: $e")
                badgesUser.text = "An error occurred: ${e.message}"
            }
        }
    }
}

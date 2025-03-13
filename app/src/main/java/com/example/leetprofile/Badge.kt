package com.example.leetprofile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.leetprofile.Dataclasses.BadgesResponse

class Badge : Fragment() {
    private lateinit var badgesUser: TextView
    private lateinit var badgesCount: TextView
    private lateinit var activeBadge: TextView
    private lateinit var upcomingBadges: TextView

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

        setupObservers()

        return view
    }

    private fun setupObservers() {
        userViewModel.userBadges.observe(viewLifecycleOwner) { badgesResponse ->
            updateUI(badgesResponse)
        }

        userViewModel.badgesError.observe(viewLifecycleOwner) { errorMessage ->
            errorMessage?.let {
                Toast.makeText(context, "Error: $it", Toast.LENGTH_SHORT).show()
                badgesUser.text = "An error occurred: $it"
            }
        }
    }

    private fun updateUI(badgesResponse: BadgesResponse) {
        val badgesText = badgesResponse.badges.takeIf { it.isNotEmpty() }?.joinToString(", ") { it.displayName }
            ?: "No badges available"
        badgesUser.text = "Badges: $badgesText"

        badgesCount.text = "Badges Count: ${badgesResponse.badges.size}"

        val upcomingText = badgesResponse.upcomingBadges.takeIf { it.isNotEmpty() }?.joinToString(", ") { it.name }
            ?: "No upcoming badges"
        upcomingBadges.text = "Upcoming Badges: $upcomingText"

        activeBadge.text = "Active Badge: ${badgesResponse.activeBadge?.displayName ?: "NULL"}"
    }
}
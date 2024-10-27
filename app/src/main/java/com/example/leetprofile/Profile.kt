package com.example.leetprofile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import java.net.HttpRetryException

class Profile : Fragment() {

    private lateinit var text: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        text = view.findViewById(R.id.profile)

        apiCall("harshroot12")

        return view
    }

    private fun apiCall(userName: String) {
        var service = UserDetails(
            acSubmission = null,
            badges = null,
            calendar = null,
            contest = null,
            solved = listOf(),
            submission = null,
            languageStats = null,
            skillStats = null,
            userProfile = null,
            userProfileCalendar = null,
            userProfileUserQuestionProgressV2 = null,
            Method = null,
            description = null
        )

        lifecycleScope.launch {
            try {
//                service = RetrofitClientInstance.api.getBadges(userName)
//                text.text = service.badges?.joinToString(", ") ?: "No badges available" // Display badges as a string
//                println("Number of solved problems: ${service.solved.size}")
            } catch (e: Exception) {
                println("Error: $e")
            } catch (e: HttpRetryException) {
                println("Retry Exception: $e")
            }
        }
    }

}

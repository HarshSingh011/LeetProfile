package com.example.leetprofile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.net.HttpRetryException

class HomeFragment : Fragment() {
    private lateinit var usernameTextView: TextView
    private lateinit var nameTextView: TextView
    private lateinit var birthdayTextView: TextView
    private lateinit var rankingTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // Initialize the TextViews
        usernameTextView = view.findViewById(R.id.usernameTextView)
        nameTextView = view.findViewById(R.id.nameTextView)
        birthdayTextView = view.findViewById(R.id.birthdayTextView)
        rankingTextView = view.findViewById(R.id.rankingTextView)

        // Call the API
        apiCall("harshroot12")

        return view
    }

    private fun apiCall(username: String) {
        lifecycleScope.launch {
            try {
                val profileResponse = RetrofitClientInstance.api.getUserProfile(username)

                println("Profile Response: $profileResponse")

                usernameTextView.text = "Username: ${profileResponse.username}"
                nameTextView.text = "Name: ${profileResponse.name}"
                birthdayTextView.text = "Birthday: ${profileResponse.birthday ?: "N/A"}"
                rankingTextView.text = "Ranking: ${profileResponse.ranking}"

            } catch (e: HttpException) {
                println("HTTP Error: ${e.code()} - ${e.message()}")
                Toast.makeText(context, "Failed to load data: ${e.message()}", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                println("Error: $e")
                Toast.makeText(context, "An error occurred: ${e.message}", Toast.LENGTH_SHORT).show()
            } catch (e: HttpRetryException) {
                println("Retry Exception: $e")
                Toast.makeText(context, "Retrying failed: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

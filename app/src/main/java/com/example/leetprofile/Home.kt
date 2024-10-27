package com.example.leetprofile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.net.HttpRetryException

class HomeFragment : Fragment() {
    private lateinit var usernameTextView: TextView
    private lateinit var nameTextView: TextView
    private lateinit var birthdayTextView: TextView
    private lateinit var rankingTextView: TextView

    private val userViewModel: UserViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        usernameTextView = view.findViewById(R.id.usernameTextView)
        nameTextView = view.findViewById(R.id.nameTextView)
        birthdayTextView = view.findViewById(R.id.birthdayTextView)
        rankingTextView = view.findViewById(R.id.rankingTextView)

        userViewModel.username.observe(viewLifecycleOwner) { username ->
            apiCall(username)
        }

        return view
    }

    private fun apiCall(username: String) {
        lifecycleScope.launch {
            try {
                val profileResponse = RetrofitClientInstance.api.getUserProfile(username)

                usernameTextView.text = "Username: ${profileResponse.username}"
                nameTextView.text = "Name: ${profileResponse.name}"
                birthdayTextView.text = "Birthday: ${profileResponse.birthday ?: "N/A"}"
                rankingTextView.text = "Ranking: ${profileResponse.ranking}"

            }  catch (e: Exception) {
                println("Error: $e")
                Toast.makeText(context, "An error occurred: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

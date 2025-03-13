package com.example.leetprofile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController

class Home : Fragment() {
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

        setupObservers()

        Log.d("Home", "onCreateView")

        return view
    }

    private fun setupObservers() {
        userViewModel.userProfile.observe(viewLifecycleOwner) { profile ->
            updateUI(profile)
        }

//         Observe error state
//        userViewModel.error.observe(viewLifecycleOwner) { errorMessage ->
//            errorMessage?.let {
//                Toast.makeText(context, "Error: $it", Toast.LENGTH_SHORT).show()
//            }
//        }
    }

    private fun updateUI(profile: com.example.leetprofile.Dataclasses.account) {
        usernameTextView.text = "Username: ${profile.username}"
        nameTextView.text = "Name: ${profile.name}"
        birthdayTextView.text = "Birthday: ${profile.birthday ?: "N/A"}"
        rankingTextView.text = "Ranking: ${profile.ranking}"
    }
}
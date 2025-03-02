package com.example.leetprofile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch

class Submission : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var submissionAdapter: SubmissionAdapter

    private val userViewModel: UserViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_submission, container, false)

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)

        userViewModel.username.observe(viewLifecycleOwner) { username ->
            apiCall(username)
        }

        return view
    }

    private fun apiCall(username: String) {
        lifecycleScope.launch {
            try {
                val response = RetrofitClientInstance.api.getSubmission(username)

                val submissions = response.submission

                submissionAdapter = SubmissionAdapter(submissions)
                recyclerView.adapter = submissionAdapter

            }catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun navigateToEntUser() {
        findNavController().popBackStack(R.id.homeFragment, true)
        findNavController().navigate(R.id.ent_user)
    }
}

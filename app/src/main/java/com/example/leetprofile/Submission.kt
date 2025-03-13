package com.example.leetprofile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.leetprofile.Dataclasses.SubmissionResponse

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

        setupObservers()

        return view
    }

    private fun setupObservers() {
        userViewModel.submissions.observe(viewLifecycleOwner) { submissionResponse ->
            updateUI(submissionResponse)
        }

        userViewModel.submissionsError.observe(viewLifecycleOwner) { errorMessage ->
            errorMessage?.let {
                Toast.makeText(context, "Error: $it", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun updateUI(submissionResponse: SubmissionResponse) {
        val submissions = submissionResponse.submission
        submissionAdapter = SubmissionAdapter(submissions)
        recyclerView.adapter = submissionAdapter
    }
}
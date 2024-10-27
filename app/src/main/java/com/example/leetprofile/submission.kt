package com.example.leetprofile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch
import retrofit2.HttpException

class Submission : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var submissionAdapter: SubmissionAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_submission, container, false)

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)

        fetchSubmissions("harshroot12")

        return view
    }

    private fun fetchSubmissions(username: String) {
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
}

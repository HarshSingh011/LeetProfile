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

class PreviousQuestions : Fragment() {
    private lateinit var solvedProblemsTextView: TextView
    private lateinit var easySolvedTextView: TextView
    private lateinit var mediumSolvedTextView: TextView
    private lateinit var hardSolvedTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_previous_questions, container, false)

        solvedProblemsTextView = view.findViewById(R.id.solvedProblemsTextView)
        easySolvedTextView = view.findViewById(R.id.easySolvedTextView)
        mediumSolvedTextView = view.findViewById(R.id.mediumSolvedTextView)
        hardSolvedTextView = view.findViewById(R.id.hardSolvedTextView)

        apiCall("harshroot12")

        return view
    }

    private fun apiCall(username: String) {
        lifecycleScope.launch {
            try {
                val problemStatsResponse = RetrofitClientInstance.api.getProblemStats(username)

                solvedProblemsTextView.text = "Solved Problems: ${problemStatsResponse.solvedProblem}"
                easySolvedTextView.text = "Easy Solved: ${problemStatsResponse.easySolved}"
                mediumSolvedTextView.text = "Medium Solved: ${problemStatsResponse.mediumSolved}"
                hardSolvedTextView.text = "Hard Solved: ${problemStatsResponse.hardSolved}"

            } catch (e: HttpException) {
                solvedProblemsTextView.text = "Failed to load data: ${e.message()}"
            } catch (e: Exception) {
                solvedProblemsTextView.text = "An error occurred: $e"
            }
        }
    }
}

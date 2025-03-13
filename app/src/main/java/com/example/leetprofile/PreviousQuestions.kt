package com.example.leetprofile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.leetprofile.Dataclasses.ProblemStatsResponse

class PreviousQuestions : Fragment() {
    private lateinit var solvedProblemsTextView: TextView
    private lateinit var easySolvedTextView: TextView
    private lateinit var mediumSolvedTextView: TextView
    private lateinit var hardSolvedTextView: TextView

    private val userViewModel: UserViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_previous_questions, container, false)

        solvedProblemsTextView = view.findViewById(R.id.solvedProblemsTextView)
        easySolvedTextView = view.findViewById(R.id.easySolvedTextView)
        mediumSolvedTextView = view.findViewById(R.id.mediumSolvedTextView)
        hardSolvedTextView = view.findViewById(R.id.hardSolvedTextView)

        setupObservers()

        return view
    }

    private fun setupObservers() {
        userViewModel.problemStats.observe(viewLifecycleOwner) { problemStats ->
            updateUI(problemStats)
        }

        userViewModel.problemStatsError.observe(viewLifecycleOwner) { errorMessage ->
            errorMessage?.let {
                Toast.makeText(context, "Error: $it", Toast.LENGTH_SHORT).show()
                solvedProblemsTextView.text = "An error occurred: $it"
                easySolvedTextView.text = ""
                mediumSolvedTextView.text = ""
                hardSolvedTextView.text = ""
            }
        }
    }

    private fun updateUI(problemStats: ProblemStatsResponse) {
        solvedProblemsTextView.text = "Solved Problems: ${problemStats.solvedProblem}"
        easySolvedTextView.text = "Easy Solved: ${problemStats.easySolved}"
        mediumSolvedTextView.text = "Medium Solved: ${problemStats.mediumSolved}"
        hardSolvedTextView.text = "Hard Solved: ${problemStats.hardSolved}"
    }
}
package com.example.leetprofile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PreviousQuestions : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MyAdapter
    private var solvedQuestionsList: List<UserDetails> = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_previous_questions, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = MyAdapter(requireContext(), solvedQuestionsList)
        recyclerView.adapter = adapter

        fetchSolvedQuestions()
    }

    private fun fetchSolvedQuestions() {
        val username = UserDetailsSingleton.userDetails.username // Get the username from the singleton
        val retrofit = Retrofit.Builder()
            .baseUrl("https://alfa-leetcode-api.onrender.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(LeetProfile_Interface::class.java)
        service.getSolvedProblems(username).enqueue(object : Callback<List<UserDetails>> {
            override fun onResponse(call: Call<List<UserDetails>>, response: Response<List<UserDetails>>) {
                if (response.isSuccessful && response.body() != null) {
                    solvedQuestionsList = response.body()!!
                    adapter = MyAdapter(requireContext(), solvedQuestionsList)
                    recyclerView.adapter = adapter
                } else {
                    Log.e("PreviousQuestions", "Error: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<UserDetails>>, t: Throwable) {
                Log.e("PreviousQuestions", "Failure: ${t.message}")
            }
        })
    }
}

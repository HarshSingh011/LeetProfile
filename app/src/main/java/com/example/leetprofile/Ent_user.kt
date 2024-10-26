package com.example.leetprofile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class Ent_user : Fragment() {
    private lateinit var usernameEditText: EditText
    private lateinit var checkButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_ent_user, container, false)

        usernameEditText = view.findViewById(R.id.username)
        checkButton = view.findViewById(R.id.check_button)

        checkButton.setOnClickListener {
            val username = usernameEditText.text.toString()

            if (username.isNotEmpty()) {
                UserDetailsSingleton.userDetails.username = username

                Toast.makeText(requireContext(), "Username updated to: ${UserDetailsSingleton.userDetails.username}", Toast.LENGTH_SHORT).show()

                usernameEditText.text.clear()
            } else {
                Toast.makeText(requireContext(), "Please enter a username", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }
}

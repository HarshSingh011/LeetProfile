package com.example.leetprofile

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

//class MyAdapter(private val context: Context, private var list: List<UserDetails>) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {
//
//    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
//        var content: TextView = view.findViewById(R.id.content)
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val view = LayoutInflater.from(context).inflate(R.layout.element, parent, false)
//        return ViewHolder(view)
//    }
//
//    override fun getItemCount(): Int {
//        return list.size
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.content.text = list[position]. // Adjust according to the UserDetails fields
//    }
//
//    fun updateList(newList: List<UserDetails>) {
//        list = newList
//        notifyDataSetChanged()
//    }
//}

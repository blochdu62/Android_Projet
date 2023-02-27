package com.example.android_projet

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListAdapter(val data: List<Int>) : RecyclerView.Adapter<ListAdapter.MyViewHolder>() {
    class MyViewHolder(val row: View) : RecyclerView.ViewHolder(row) {
        val textView = row.findViewById<TextView>(R.id.number)

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder { val layout = LayoutInflater.from(parent.context).inflate(R.layout.result_view,
        parent, false)
        return MyViewHolder(layout)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.textView.text = data.get(position).toString()
    }
    override fun getItemCount(): Int = data.size }
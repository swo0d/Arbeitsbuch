package com.example.arbeitsbuch.presentation

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.arbeitsbuch.R

class ObjectItemViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    val tvName = view.findViewById<TextView>(R.id.list_item_object_name)

    val tvDate = view.findViewById<TextView>(R.id.list_item_object_date)
}
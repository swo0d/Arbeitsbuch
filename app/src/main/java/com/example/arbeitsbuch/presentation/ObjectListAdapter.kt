package com.example.arbeitsbuch.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.arbeitsbuch.R
import com.example.arbeitsbuch.domain.ObjectItem

class ObjectListAdapter : RecyclerView.Adapter<ObjectListAdapter.ObjectItemViewHolder>() {

    val list = listOf<ObjectItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ObjectItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_object_list_disabled, parent, false)
        return ObjectItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(viewHolder: ObjectItemViewHolder, position: Int) {
        val objectItem = list[position]
        viewHolder.tvName.text = objectItem.name
        viewHolder.tvDate.text = objectItem.date.toString()
        viewHolder.view.setOnLongClickListener {
            true
        }
    }

    class ObjectItemViewHolder(val view: View) : RecyclerView.ViewHolder(view){
        val tvName = view.findViewById<TextView>(R.id.list_item_object_name)
        val tvDate = view.findViewById<TextView>(R.id.list_item_object_date)
    }
}
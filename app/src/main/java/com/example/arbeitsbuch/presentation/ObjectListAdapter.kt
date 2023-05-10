package com.example.arbeitsbuch.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.arbeitsbuch.R
import com.example.arbeitsbuch.domain.ObjectItem

class ObjectListAdapter : RecyclerView.Adapter<ObjectListAdapter.ObjectItemViewHolder>() {

    var objectList = listOf<ObjectItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ObjectItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_object_list_disabled, parent, false)
        return ObjectItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return objectList.size
    }

    override fun onBindViewHolder(viewHolder: ObjectItemViewHolder, position: Int) {
        val objectItem = objectList[position]
        val status = if (objectItem.enabled) {
            "Active"
        } else {
            "Not active"
        }

        viewHolder.view.setOnLongClickListener {
            true
        }
        if (objectItem.enabled) {
            viewHolder.tvName.text = "${objectItem.name} $status"
            viewHolder.tvDate.text = objectItem.date.toString()
            viewHolder.tvName.setTextColor(ContextCompat.getColor(viewHolder.view.context, android.R.color.holo_red_dark))
        }
    }

    override fun onViewRecycled(viewHolder: ObjectItemViewHolder) {
        viewHolder.tvName.text = ""
        viewHolder.tvDate.text = ""
        viewHolder.tvName.setTextColor(ContextCompat
            .getColor(viewHolder.view.context, android.R.color.white))
    }

    class ObjectItemViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val tvName = view.findViewById<TextView>(R.id.list_item_object_name)
        val tvDate = view.findViewById<TextView>(R.id.list_item_object_date)
    }
}
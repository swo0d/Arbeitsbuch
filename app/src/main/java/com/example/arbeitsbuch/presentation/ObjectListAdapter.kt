package com.example.arbeitsbuch.presentation

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ListAdapter
import com.example.arbeitsbuch.R
import com.example.arbeitsbuch.domain.ObjectItem

//class ObjectListAdapter : RecyclerView.Adapter<ObjectListAdapter.ObjectItemViewHolder>() {
class ObjectListAdapter :
    ListAdapter<ObjectItem, ObjectItemViewHolder>(ObjectItemDiffCallback()) {
    var count = 0


    var onObjectItemLongClickListener: ((ObjectItem) -> Unit)? = null
    var onObjectItemClickListener: ((ObjectItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ObjectItemViewHolder {
        // Log.d("ObjectListAdapter","onCreateViewHolder, count: ${++count}")
        val layout = when (viewType) {
            VIEW_TYPE_ENABLED -> R.layout.item_object_list_enabled
            VIEW_TYPE_DISABLED -> R.layout.item_object_list_disabled
            else -> throw java.lang.RuntimeException("Unknown view type: $viewType")
        }
        val view = LayoutInflater.from(parent.context)
            .inflate(layout, parent, false)
        return ObjectItemViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ObjectItemViewHolder, position: Int) {
        Log.d("ObjectListAdapter", "onBindViewHolder, count: ${++count}")
        val objectItem = getItem(position)
        viewHolder.view.setOnLongClickListener {
            onObjectItemLongClickListener?.invoke(objectItem)
            true
        }
        viewHolder.view.setOnClickListener {
            onObjectItemClickListener?.invoke(objectItem)
            true
        }
        viewHolder.tvName.text = objectItem.name
        viewHolder.tvDate.text = objectItem.date.toString()
    }

    override fun onViewRecycled(viewHolder: ObjectItemViewHolder) {
        viewHolder.tvName.text = ""
        viewHolder.tvDate.text = ""
        viewHolder.tvName.setTextColor(
            ContextCompat
                .getColor(viewHolder.view.context, android.R.color.white)
        )
    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return if (item.enabled) {
            VIEW_TYPE_ENABLED
        } else {
            VIEW_TYPE_DISABLED
        }
    }
    companion object {
        const val VIEW_TYPE_ENABLED = 100
        const val VIEW_TYPE_DISABLED = 101
        const val MAX_POOL_SIZE = 10
    }
}
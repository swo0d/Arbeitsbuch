package com.example.arbeitsbuch.presentation

import androidx.recyclerview.widget.DiffUtil
import com.example.arbeitsbuch.domain.ObjectItem

class ObjectItemDiffCallback:DiffUtil.ItemCallback<ObjectItem>(){

    override fun areItemsTheSame(oldItem: ObjectItem, newItem: ObjectItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ObjectItem, newItem: ObjectItem): Boolean {
        return oldItem == newItem
    }

}
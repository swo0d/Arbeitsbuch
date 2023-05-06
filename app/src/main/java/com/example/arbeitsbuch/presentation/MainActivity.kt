package com.example.arbeitsbuch.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.arbeitsbuch.R
import com.example.arbeitsbuch.domain.ObjectItem

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var llObjectList: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        llObjectList = findViewById(R.id.ll_object_list)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.objectList.observe(this) {
          showList(it)
        }
    }

    private fun showList(list: List<ObjectItem>) {
        llObjectList.removeAllViews()
        for (objectItem in list) {
            val layoutId = if (objectItem.enabled) {
            R.layout.item_object_list_enabled
        } else {
            R.layout.item_object_list_disabled
        }
        val view = LayoutInflater.from(this).inflate(layoutId, llObjectList, false)
        val tvName = view.findViewById<TextView>(R.id.list_item_object_name)
        val tvDate = view.findViewById<TextView>(R.id.list_item_object_date)
        tvName.text = objectItem.name
        tvDate.text = objectItem.date.toString()
        view.setOnLongClickListener {
                viewModel.changeEnableState(objectItem)
                true
            }
        llObjectList.addView(view)
        }
    }
}
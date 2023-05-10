package com.example.arbeitsbuch.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.RecoverySystem
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.arbeitsbuch.R
import com.example.arbeitsbuch.domain.ObjectItem

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: ObjectListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupRecyclerView()
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.objectList.observe(this) {
            adapter.objectList = it
        }
    }

    private fun setupRecyclerView() {
        val rvObjectList = findViewById<RecyclerView>(R.id.rv_object_list)
        adapter = ObjectListAdapter()
        rvObjectList.adapter = adapter
    }

}
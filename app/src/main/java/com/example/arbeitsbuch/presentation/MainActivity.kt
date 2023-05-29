package com.example.arbeitsbuch.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.arbeitsbuch.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), ObjectItemFragment.OnEditingFinishedListener {

    private lateinit var viewModel: MainViewModel
    private lateinit var objectListAdapter: ObjectListAdapter
    private var objectItemContainer: FragmentContainerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        objectItemContainer = findViewById(R.id.object_item_container)
        setupRecyclerView()
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.objectList.observe(this) {
            objectListAdapter.submitList(it)
        }
        val buttonAddObject = findViewById<FloatingActionButton>(R.id.button_add_new_object)
        buttonAddObject.setOnClickListener {
            if(isOnePaneMode()) {
                val intent = ObjectItemActivity.newIntentAddObject(this)
                startActivity(intent)
            } else {
                launchFragment(ObjectItemFragment.newInstanceAddItem())
            }

        }
    }

    override fun onEditingFinished() {
        Toast.makeText(this@MainActivity, "Success", Toast.LENGTH_SHORT).show()
        supportFragmentManager.popBackStack()
    }

    private fun isOnePaneMode(): Boolean {
        return objectItemContainer == null
    }

    private fun launchFragment(fragment: Fragment) {
        supportFragmentManager.popBackStack()
        supportFragmentManager.beginTransaction()
            .replace(R.id.object_item_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    private fun setupRecyclerView() {
        val rvObjectList = findViewById<RecyclerView>(R.id.rv_object_list)
        with(rvObjectList) {
            objectListAdapter = ObjectListAdapter()
            adapter = objectListAdapter
            recycledViewPool.setMaxRecycledViews(
                ObjectListAdapter.VIEW_TYPE_ENABLED,
                ObjectListAdapter.MAX_POOL_SIZE
            )
            recycledViewPool.setMaxRecycledViews(
                ObjectListAdapter.VIEW_TYPE_DISABLED,
                ObjectListAdapter.MAX_POOL_SIZE
            )
        }
        setupLongClickListener()
        setupClickListener()
        setupSwipeListener(rvObjectList)
    }

    private fun setupSwipeListener(rvObjectList: RecyclerView) {
        val callback = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = objectListAdapter.currentList[viewHolder.adapterPosition]
                viewModel.deleteObjectItem(item)
            }

        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(rvObjectList)
    }

    private fun setupClickListener() {
        objectListAdapter.onObjectItemClickListener = {
            if(isOnePaneMode()) {
                val intent = ObjectItemActivity.newIntentEditObject(this, it.id)
                startActivity(intent)
            }else {
                launchFragment(ObjectItemFragment.newInstanceEditItem(it.id))
            }
        }
    }

    private fun setupLongClickListener() {
        objectListAdapter.onObjectItemLongClickListener = {
            viewModel.changeEnableState(it)
        }
    }
}
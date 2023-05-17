package com.example.arbeitsbuch.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.arbeitsbuch.R

class ObjectItemActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item_object)
        val mode = intent.getStringExtra(EXTRA_SCREEN_MODE)
        Log.d("ObjectItemActivity", mode.toString())
    }


    companion object {

        private const val EXTRA_SCREEN_MODE = "extra_mode"
        private const val EXTRA_OBJECT_ITEM_ID = " extra_object_item_id"
        private const val MODE_EDIT = "mode_edit"
        private const val MODE_ADD = "mode_add"

        fun newIntentAddObject(context: Context): Intent {
            val intent = Intent(context,ObjectItemActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MODE, MODE_ADD)
            return intent
        }

        fun newIntentEditObject(context: Context): Intent {
            val intent = Intent(context,ObjectItemActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MODE, MODE_EDIT)
            //intent.putExtra(EXTRA_OBJECT_ITEM_ID, objectItemId)
            return intent
        }
    }
}
package com.example.arbeitsbuch.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.example.arbeitsbuch.R
import com.example.arbeitsbuch.domain.ObjectItem
import com.google.android.material.textfield.TextInputLayout

class ObjectItemActivity : AppCompatActivity(), ObjectItemFragment.OnEditingFinishedListener {

    //    private lateinit var viewModel: ObjectItemViewModel
//
//    private lateinit var tvObjectName: TextView
//    private lateinit var etObjectName: EditText
//    private lateinit var buttonSave: Button
//
    private var screenMode = MODE_UNKNOWN
    private var objectItemId = ObjectItem.UNDEFINED_ID
//
    override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.item_object)
    parseIntent()
    if (savedInstanceState == null) {
        changeRightMode()
    }
}
    override fun onEditingFinished() {
        finish()
    }
    private fun changeRightMode() {
       val fragment = when (screenMode) {
            MODE_EDIT -> ObjectItemFragment.newInstanceEditItem(objectItemId)
            MODE_ADD -> ObjectItemFragment.newInstanceAddItem()
           else ->  throw java.lang.RuntimeException("Unknown screen mode $screenMode")
        }
        supportFragmentManager.beginTransaction()
            .add(R.id.object_item_container, fragment)
            .commit()
    }

    private fun parseIntent() {
        if (!intent.hasExtra(EXTRA_SCREEN_MODE)) {
            throw RuntimeException("Param screen mode is absent")
        }
        val mode = intent.getStringExtra(EXTRA_SCREEN_MODE)
        if (mode != MODE_EDIT && mode != MODE_ADD) {
            throw java.lang.RuntimeException("Unknown screen mode $mode")
        }
        screenMode = mode
        if (screenMode == MODE_EDIT) {
            if (!intent.hasExtra(EXTRA_OBJECT_ITEM_ID)) {
                throw RuntimeException("Param object item id in absent")
            }
            objectItemId = intent.getIntExtra(EXTRA_OBJECT_ITEM_ID, ObjectItem.UNDEFINED_ID)
        }
    }

        companion object {

            private const val EXTRA_SCREEN_MODE = "extra_mode"
            private const val EXTRA_OBJECT_ITEM_ID = " extra_object_item_id"
            private const val MODE_EDIT = "mode_edit"
            private const val MODE_ADD = "mode_add"
            private const val MODE_UNKNOWN = ""


            fun newIntentAddObject(context: Context): Intent {
                val intent = Intent(context, ObjectItemActivity::class.java)
                intent.putExtra(EXTRA_SCREEN_MODE, MODE_ADD)
                return intent
            }

            fun newIntentEditObject(context: Context, objectItemId: Int): Intent {
                val intent = Intent(context, ObjectItemActivity::class.java)
                intent.putExtra(EXTRA_SCREEN_MODE, MODE_EDIT)
                intent.putExtra(EXTRA_OBJECT_ITEM_ID, objectItemId)
                return intent
            }
        }
    }
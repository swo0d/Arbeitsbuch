package com.example.arbeitsbuch.presentation

import android.content.Context
import android.content.Intent
import android.content.Intent.parseIntent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.arbeitsbuch.R
import com.example.arbeitsbuch.domain.ObjectItem

class ObjectItemFragment: Fragment() {

    private lateinit var viewModel: ObjectItemViewModel

    private lateinit var tvObjectName: TextView
    private lateinit var etObjectName: EditText
    private lateinit var buttonSave: Button

    private var screenMode: String = MODE_UNKNOWN
    private var objectItemId: Int = ObjectItem.UNDEFINED_ID

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseParams()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
     // из макета создаем view = inflater.inflate(R.layout.fragment_object_item, container, false)
        return inflater.inflate(R.layout.fragment_object_item, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[ObjectItemViewModel::class.java]
        initViews(view)
        addTextChangeListener()
        changeRightMode()
        observeViewModel()
    }
    private fun observeViewModel() {
        viewModel.errorInputName.observe(viewLifecycleOwner) {
            val message = if (it) {
                getString(R.string.error_input_name)
            } else {
                null
            }
            etObjectName.error = message
        }
        viewModel.shouldCloseScreen.observe(viewLifecycleOwner) {
            activity?.onBackPressedDispatcher?.onBackPressed()
        }
    }

    private fun changeRightMode() {
        when (screenMode) {
            MODE_EDIT -> launchEditMode()
            MODE_ADD -> launchAddMode()
        }
    }

    private fun addTextChangeListener() {
        etObjectName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.resetErrorInputName()
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })
    }

    private fun launchEditMode() {
        bSaveVisibility()
        viewModel.getObjectItem(objectItemId)
        viewModel.objectItem.observe(viewLifecycleOwner) {
            etObjectName.setText(it.name)
        }
        buttonSave.setOnClickListener {
            viewModel.editObjectItem(etObjectName.text?.toString())
        }
    }

    private fun launchAddMode() {
        bSaveVisibility()
        buttonSave.setOnClickListener {
            viewModel.addObjectItem(etObjectName.text?.toString())

        }
    }

    private fun bSaveVisibility() {
        buttonSave.visibility = View.VISIBLE
    }

    private fun parseParams() {
        val args = requireArguments()
        if (!args.containsKey(SCREEN_MODE)) {
            throw RuntimeException("Param screen mode is absent")
        }
        val mode = args.getString(SCREEN_MODE)
        if (mode != MODE_EDIT && mode != MODE_ADD) {
            throw java.lang.RuntimeException("Unknown screen mode $mode")
        }
        screenMode = mode
        if (screenMode == MODE_EDIT) {
            if (!args.containsKey(OBJECT_ITEM_ID)) {
                throw RuntimeException("Param object item id in absent")
            }
            objectItemId = args.getInt(OBJECT_ITEM_ID, ObjectItem.UNDEFINED_ID)
        }
    }


    private fun initViews(view:View) {
        tvObjectName = view.findViewById(R.id.tv_object_name)
        etObjectName = view.findViewById(R.id.et_object_name)
        buttonSave = view.findViewById(R.id.b_save)

    }

    companion object {

        private const val SCREEN_MODE = "extra_mode"
        private const val OBJECT_ITEM_ID = " extra_object_item_id"
        private const val MODE_EDIT = "mode_edit"
        private const val MODE_ADD = "mode_add"
        private const val MODE_UNKNOWN = ""


        fun newInstanceAddItem(): ObjectItemFragment {
            return ObjectItemFragment().apply {
                arguments = Bundle().apply {
                    putString(SCREEN_MODE, MODE_ADD)
                }
            }
        }

        fun newInstanceEditItem(objectItemId: Int): ObjectItemFragment {
            return ObjectItemFragment().apply {
                arguments = Bundle().apply {
                    putString(SCREEN_MODE, MODE_EDIT)
                    putInt(OBJECT_ITEM_ID, objectItemId)
                }
            }
        }
    }
}
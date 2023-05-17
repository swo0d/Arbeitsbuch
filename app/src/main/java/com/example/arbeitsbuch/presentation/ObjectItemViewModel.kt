package com.example.arbeitsbuch.presentation

import android.graphics.drawable.LayerDrawable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.arbeitsbuch.data.ObjectListRepositoryImpl
import com.example.arbeitsbuch.domain.AddObjectItemUseCase
import com.example.arbeitsbuch.domain.EditObjectItemUseCase
import com.example.arbeitsbuch.domain.GetObjectItemUseCase
import com.example.arbeitsbuch.domain.ObjectItem
import java.util.Date

class ObjectItemViewModel : ViewModel() {
    private val repository = ObjectListRepositoryImpl

    private val getObjectItemUseCase = GetObjectItemUseCase(repository)
    private val addObjectItemUseCase = AddObjectItemUseCase(repository)
    private val editObjectItemUseCase = EditObjectItemUseCase(repository)

    private val _errorInputName = MutableLiveData<Boolean>()
    val errorInputName: LiveData<Boolean>
        get() = _errorInputName

    private val _objectItem = MutableLiveData<ObjectItem>()
    val objectItem: LiveData<ObjectItem>
        get() = _objectItem

    private val _shouldCloseScreen = MutableLiveData<Unit>()
    val shouldCloseScreen: LiveData<Unit>
        get() = _shouldCloseScreen


    fun getObjectItem(objectItemId: Int) {
        val item = getObjectItemUseCase.getObjectItem(objectItemId)
        _objectItem.value = item
    }

    fun addObjectItem(inputName: String?) {
        val name = parseName(inputName)
        val fieldValid = validateInput(name)
        if (fieldValid) {
            val objectItem = ObjectItem(name, Date(), true, false)
            addObjectItemUseCase.addObjectItem(objectItem)
            finishWork()
        }

    }

    fun editObjectItem(inputName: String?) {
        val name = parseName(inputName)
        val fieldValid = validateInput(name)
        if (fieldValid) {
            _objectItem.value?.let {
                val item = it.copy(name = name)
                editObjectItemUseCase.editObjectItem(item)
                finishWork()
            }
        }
    }

    private fun parseName(inputName: String?): String {
        return inputName?.trim() ?: ""
    }

    private fun validateInput(name: String): Boolean {
        var result = true
        if (name.isBlank()) {
           _errorInputName.value = true
            result = false
        }
        return result
    }

    fun resetErrorInputName() {
        _errorInputName.value = false
    }

    private fun finishWork() {
        _shouldCloseScreen.value = Unit
    }
}

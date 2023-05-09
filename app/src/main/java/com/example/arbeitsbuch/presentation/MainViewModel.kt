package com.example.arbeitsbuch.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.arbeitsbuch.data.ObjectListRepositoryImpl// дата слой не должен здесь представлен
import com.example.arbeitsbuch.domain.DeleteObjectItemUseCase
import com.example.arbeitsbuch.domain.EditObjectItemUseCase
import com.example.arbeitsbuch.domain.GetObjectListUseCase
import com.example.arbeitsbuch.domain.ObjectItem

class MainViewModel : ViewModel() {
    // неправильная реализация для repository ( без инекций )
    private val repository = ObjectListRepositoryImpl

    // три элемента бизнес логики
    private val getObjectListUseCase = GetObjectListUseCase(repository)
    private val deleteObjectItemUseCase = DeleteObjectItemUseCase(repository)
    private val editObjectItemUseCase = EditObjectItemUseCase(repository)

    val objectList = getObjectListUseCase.getObjectList()

    // delete object
    fun deleteObjectItem(objectItem: ObjectItem){
        deleteObjectItemUseCase.deleteObject(objectItem)
    }
    // edit object enabled
    fun changeEnableState(objectItem: ObjectItem){
        val newItem = objectItem.copy(enabled = !objectItem.enabled)
        editObjectItemUseCase.editObjectItem(newItem)


    }
    // edit object solved
    fun changeSolvedState(objectItem: ObjectItem){
        val newItem = objectItem.copy(isSolved = !objectItem.isSolved)
        editObjectItemUseCase.editObjectItem(newItem)

    }


}
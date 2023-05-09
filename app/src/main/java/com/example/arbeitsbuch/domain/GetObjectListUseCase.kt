package com.example.arbeitsbuch.domain

import androidx.lifecycle.LiveData

class GetObjectListUseCase(private val objectListRepository: ObjectListRepository) {

    fun getObjectList() : LiveData<List<ObjectItem>>{
        return objectListRepository.getObjectList()
    }
}
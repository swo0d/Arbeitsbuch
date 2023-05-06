package com.example.arbeitsbuch.domain

class GetObjectListUseCase(private val objectListRepository: ObjectListRepository) {

    fun getObjectList() : List<ObjectItem>{
        return objectListRepository.getObjectList()
    }
}
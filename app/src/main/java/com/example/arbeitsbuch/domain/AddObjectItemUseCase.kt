package com.example.arbeitsbuch.domain

class AddObjectItemUseCase(private val objectListRepository: ObjectListRepository) {

    fun addObjectItem(objectItem: ObjectItem){
        objectListRepository.addObjectItem(objectItem)
    }

}
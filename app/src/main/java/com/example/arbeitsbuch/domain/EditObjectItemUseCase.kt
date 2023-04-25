package com.example.arbeitsbuch.domain

class EditObjectItemUseCase(private val objectListRepository: ObjectListRepository) {

    fun editObjectItem(objectItem: ObjectItem){
        objectListRepository.editObjectItem(objectItem)
    }
}
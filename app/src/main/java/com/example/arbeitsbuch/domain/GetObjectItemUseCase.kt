package com.example.arbeitsbuch.domain

class GetObjectItemUseCase(private val objectListRepository: ObjectListRepository) {

    fun getObjectItem(objectItemId: Int) : ObjectItem {
       return objectListRepository.getObjectItem(objectItemId)
    }
}
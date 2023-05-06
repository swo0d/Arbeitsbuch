package com.example.arbeitsbuch.domain

class DeleteObjectItemUseCase(private val objectListRepository: ObjectListRepository) {

    fun deleteObject(objectItem: ObjectItem){
        objectListRepository.deleteObjectItem(objectItem)

    }
}
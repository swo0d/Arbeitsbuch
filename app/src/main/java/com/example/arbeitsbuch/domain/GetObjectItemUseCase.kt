package com.example.arbeitsbuch.domain

import java.util.UUID

class GetObjectItemUseCase(private val objectListRepository: ObjectListRepository) {

   fun getObjectItem(objectItemId: Int) : ObjectItem {

       return objectListRepository.getObjectItem(objectItemId)
    }
}
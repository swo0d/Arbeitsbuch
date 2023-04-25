package com.example.arbeitsbuch.data

import com.example.arbeitsbuch.domain.ObjectItem
import com.example.arbeitsbuch.domain.ObjectListRepository

class ObjectListRepositoryImpl : ObjectListRepository {

    private val objectList = mutableListOf<ObjectItem>()
    private var autoIncrementId = 0

    override fun addObjectItem(objectItem: ObjectItem) {
        if (objectItem.id == ObjectItem.UNDEFINED_ID) {
            objectItem.id = autoIncrementId++
        }
        objectList.add(objectItem)

    }

    override fun deleteObjectItem(objectItem: ObjectItem) {
        objectList.remove(objectItem)
    }

    override fun editObjectItem(objectItem: ObjectItem) {
        val oldElement = getObjectItem(objectItem.id)
        objectList.remove(oldElement)
        addObjectItem(objectItem)

    }

    override fun getObjectItem(objectItemId: Int): ObjectItem {
        return objectList.find{
            it.id == objectItemId
        }?: throw RuntimeException("Element with id $objectItemId not found")

    }

    override fun getObjectList(): List<ObjectItem> {
        return objectList.toList()
    }

}
package com.example.arbeitsbuch.domain

interface ObjectListRepository {

    fun addObjectItem(objectItem: ObjectItem)

    fun deleteObjectItem(objectItem: ObjectItem)

    fun editObjectItem(objectItem: ObjectItem)

    fun getObjectItem(objectItemId: Int) : ObjectItem

    fun getObjectList(): List<ObjectItem>
}
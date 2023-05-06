package com.example.arbeitsbuch.domain

import androidx.lifecycle.LiveData
import java.util.*

interface ObjectListRepository {

    fun addObjectItem(objectItem: ObjectItem)

    fun deleteObjectItem(objectItem: ObjectItem)

    fun editObjectItem(objectItem: ObjectItem)

    fun getObjectItem(objectItemId: Int) : ObjectItem

    fun getObjectList(): LiveData<List<ObjectItem>>
}
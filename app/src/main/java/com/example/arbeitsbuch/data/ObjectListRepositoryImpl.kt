package com.example.arbeitsbuch.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.arbeitsbuch.domain.ObjectItem
import com.example.arbeitsbuch.domain.ObjectListRepository
import java.util.*
import kotlin.random.Random

object ObjectListRepositoryImpl : ObjectListRepository {

    private val objectListLD = MutableLiveData<List<ObjectItem>>()
    private val objectList = sortedSetOf<ObjectItem>({o1, o2 -> o1.id.compareTo(o2.id)})

    private var autoIncrementId = 0
    init {
        for (i in 0 until 3) {
            val item = ObjectItem("Name = Object-$i", date= Date(), Random.nextBoolean(), false)
            addObjectItem(item)
        }
    }

    override fun addObjectItem(objectItem: ObjectItem) {
        if (objectItem.id == ObjectItem.UNDEFINED_ID) {
            objectItem.id = autoIncrementId++
        }
        objectList.add(objectItem)
        updateObjectList()
    }

    override fun deleteObjectItem(objectItem: ObjectItem) {
        objectList.remove(objectItem)
        updateObjectList()
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

    override fun getObjectList(): LiveData<List<ObjectItem>> {
        return objectListLD
    }
    // update objectList
    fun updateObjectList(){
        objectListLD.value = objectList.toList()
    }
}
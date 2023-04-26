package com.example.arbeitsbuch.domain

import java.util.Date
import java.util.UUID

data class ObjectItem(

    val name: String = "",
    val date: Date = Date(),
    val enabled: Boolean = false,
    val isSolved: Boolean = false,
    //var id: UUID = UUID.randomUUID()
    var id: Int = UNDEFINED_ID


)
{
    companion object{
        const val UNDEFINED_ID = -1
    }
}
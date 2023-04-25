package com.example.arbeitsbuch.domain

import java.util.Date

data class ObjectItem(

    val name: String = "",
    val date: Date = Date(),
    val enabled: Boolean = false,
    var id: Int = UNDEFINED_ID


)
{
    companion object{
        const val UNDEFINED_ID = -1
    }
}
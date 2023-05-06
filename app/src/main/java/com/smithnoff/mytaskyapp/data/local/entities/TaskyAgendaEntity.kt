package com.smithnoff.mytaskyapp.data.local.entities

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

abstract class TaskyAgendaEntity {
    abstract val id: Int?

    abstract val title: String

    abstract val description: String?

    abstract val remindAt:Long
}
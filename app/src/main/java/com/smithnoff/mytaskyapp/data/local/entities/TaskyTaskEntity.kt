package com.smithnoff.mytaskyapp.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task_table")
data class TaskyTaskEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id")
    override val id: Int? = null,
    @ColumnInfo("title")
    override val title: String,
    @ColumnInfo("description")
    override val description: String?,
    @ColumnInfo("remindAt")
    override val remindAt: Long,
    @ColumnInfo("time")
    val time: Long,
    @ColumnInfo("isDone")
    val isDone: Boolean
) : TaskyAgendaEntity()



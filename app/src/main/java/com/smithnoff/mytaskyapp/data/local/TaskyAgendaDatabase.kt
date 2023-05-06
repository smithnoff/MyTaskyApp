package com.smithnoff.mytaskyapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.smithnoff.mytaskyapp.data.local.dao.TaskyTaskDao
import com.smithnoff.mytaskyapp.data.local.entities.TaskyTaskEntity

@Database(entities = [TaskyTaskEntity::class], version = 1)
abstract class TaskyAgendaDatabase: RoomDatabase() {

    abstract fun taskDao():TaskyTaskDao
}
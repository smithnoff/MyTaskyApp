package com.smithnoff.mytaskyapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.smithnoff.mytaskyapp.data.local.entities.TaskyTaskEntity
import com.smithnoff.mytaskyapp.utils.Resource
import retrofit2.Response

@Dao
interface TaskyTaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTask(taskDao: TaskyTaskEntity)
    @Query("SELECT * FROM task_table where date = :date")
    suspend fun getAgendaLocalItems(date:String): List<TaskyTaskEntity>
}
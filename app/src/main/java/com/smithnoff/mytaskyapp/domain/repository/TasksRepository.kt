package com.smithnoff.mytaskyapp.domain.repository

import com.smithnoff.mytaskyapp.data.models.TaskyTask
import com.smithnoff.mytaskyapp.utils.Resource

interface TasksRepository {
    suspend fun createTask(taskTask: TaskyTask): Resource<Unit>
    suspend fun updateTask(taskTask: TaskyTask): Resource<Unit>
}
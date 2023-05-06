package com.smithnoff.mytaskyapp.domain.repository

import com.smithnoff.mytaskyapp.data.local.entities.TaskyTaskEntity
import com.smithnoff.mytaskyapp.data.models.TaskyTask
import com.smithnoff.mytaskyapp.utils.Resource
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

interface TasksRepository {
    suspend fun createTask(taskTask: TaskyTask): Resource<Unit>
    suspend fun createLocalTask(taskTask: TaskyTaskEntity)
    suspend fun updateTask(taskTask: TaskyTask): Resource<Unit>
}
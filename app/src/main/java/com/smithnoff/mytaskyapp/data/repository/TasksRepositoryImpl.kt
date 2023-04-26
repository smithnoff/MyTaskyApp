package com.smithnoff.mytaskyapp.data.repository

import com.smithnoff.mytaskyapp.data.models.TaskyTask
import com.smithnoff.mytaskyapp.data.remote.TaskyTaskApi
import com.smithnoff.mytaskyapp.domain.repository.TasksRepository
import com.smithnoff.mytaskyapp.utils.Resource
import com.smithnoff.mytaskyapp.utils.safeApiCall
import javax.inject.Inject

class TasksRepositoryImpl @Inject constructor(private val api: TaskyTaskApi) : TasksRepository {

    override suspend fun createTask(taskTask: TaskyTask): Resource<Unit> {
        return safeApiCall { api.createTask(taskTask) }
    }

    override suspend fun updateTask(taskTask: TaskyTask): Resource<Unit> {
        return safeApiCall { api.updateTask(taskTask) }
    }
}
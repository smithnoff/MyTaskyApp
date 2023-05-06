package com.smithnoff.mytaskyapp.data.repository

import com.smithnoff.mytaskyapp.data.local.dao.TaskyTaskDao
import com.smithnoff.mytaskyapp.data.local.entities.TaskyTaskEntity
import com.smithnoff.mytaskyapp.data.models.TaskyTask
import com.smithnoff.mytaskyapp.data.remote.TaskyTaskApi
import com.smithnoff.mytaskyapp.domain.repository.TasksRepository
import com.smithnoff.mytaskyapp.utils.Resource
import com.smithnoff.mytaskyapp.utils.safeApiCall
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import javax.inject.Inject
class TasksRepositoryImpl @Inject constructor(private val api: TaskyTaskApi,
                                              private val taskDao: TaskyTaskDao) : TasksRepository {

    override suspend fun createTask(taskTask: TaskyTask): Resource<Unit> {
        return safeApiCall { api.createTask(taskTask) }
    }

    override suspend fun createLocalTask(taskTask: TaskyTaskEntity) {
        return  taskDao.addTask(taskTask)
    }

    override suspend fun updateTask(taskTask: TaskyTask): Resource<Unit> {
        return safeApiCall { api.updateTask(taskTask) }
    }
}
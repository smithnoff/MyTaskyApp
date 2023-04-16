package com.smithnoff.mytaskyapp.data.repository

import com.smithnoff.mytaskyapp.data.models.TaskyReminder
import com.smithnoff.mytaskyapp.data.models.TaskyTask
import com.smithnoff.mytaskyapp.data.remote.TaskyReminderApi
import com.smithnoff.mytaskyapp.data.remote.TaskyTaskApi
import com.smithnoff.mytaskyapp.domain.repository.ReminderRepository
import com.smithnoff.mytaskyapp.domain.repository.TasksRepository
import com.smithnoff.mytaskyapp.utils.Resource
import com.smithnoff.mytaskyapp.utils.safeApiCall
import javax.inject.Inject

class ReminderRepositoryImpl @Inject constructor(private val api: TaskyReminderApi) : ReminderRepository {

    override suspend fun createReminder(taskReminder: TaskyReminder): Resource<Unit> {
        return safeApiCall { api.createReminder(taskReminder) }
    }
}
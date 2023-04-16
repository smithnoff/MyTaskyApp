package com.smithnoff.mytaskyapp.domain.repository

import com.smithnoff.mytaskyapp.data.models.TaskyReminder
import com.smithnoff.mytaskyapp.data.models.TaskyTask
import com.smithnoff.mytaskyapp.utils.Resource

interface ReminderRepository {

    suspend fun createReminder(taskReminder: TaskyReminder): Resource<Unit>
}
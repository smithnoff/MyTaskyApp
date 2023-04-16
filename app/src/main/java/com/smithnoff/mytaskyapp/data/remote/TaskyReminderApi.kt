package com.smithnoff.mytaskyapp.data.remote

import com.smithnoff.mytaskyapp.data.models.TaskyReminder
import com.smithnoff.mytaskyapp.data.models.TaskyTask
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface TaskyReminderApi {

    @POST("/reminder")
    suspend fun createReminder(@Body reminderRequest: TaskyReminder): Response<Unit>
}
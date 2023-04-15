package com.smithnoff.mytaskyapp.data.remote

import com.smithnoff.mytaskyapp.data.models.TaskyTask
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface TaskyTaskApi {

    @POST("/task")
    suspend fun createTask(@Body taskRequest: TaskyTask): Response<Unit>
}
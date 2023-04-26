package com.smithnoff.mytaskyapp.data.remote

import com.smithnoff.mytaskyapp.data.models.TaskyTask
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.PUT

interface TaskyTaskApi {

    @POST("/task")
    suspend fun createTask(@Body taskRequest: TaskyTask): Response<Unit>

    @PUT("/task")
    suspend fun updateTask(@Body taskRequest: TaskyTask): Response<Unit>
}
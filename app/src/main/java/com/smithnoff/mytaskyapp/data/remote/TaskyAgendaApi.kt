package com.smithnoff.mytaskyapp.data.remote

import com.smithnoff.mytaskyapp.data.models.AgendaResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TaskyAgendaApi {

    @GET("/agenda")
    suspend fun getAgenda(
        @Query("timezone") timeZone: String,
        @Query("time") time: Long
    ): Response<AgendaResponse>
}
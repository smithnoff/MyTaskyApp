package com.smithnoff.mytaskyapp.data.repository

import com.smithnoff.mytaskyapp.data.models.AgendaResponse
import com.smithnoff.mytaskyapp.data.remote.TaskyAgendaApi
import com.smithnoff.mytaskyapp.domain.repository.AgendaRepository
import com.smithnoff.mytaskyapp.utils.Resource
import com.smithnoff.mytaskyapp.utils.safeApiCall
import javax.inject.Inject

class AgendaRepositoryImpl @Inject constructor(private val api: TaskyAgendaApi) : AgendaRepository {

    override suspend fun getAgendaItemsByDay(timezone: String, time: Long) : Resource<AgendaResponse> {
        return safeApiCall { api.getAgenda(timezone,time) }
    }
}
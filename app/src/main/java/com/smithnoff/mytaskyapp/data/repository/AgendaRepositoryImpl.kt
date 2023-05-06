package com.smithnoff.mytaskyapp.data.repository

import com.smithnoff.mytaskyapp.data.local.dao.TaskyTaskDao
import com.smithnoff.mytaskyapp.data.local.entities.TaskyTaskEntity
import com.smithnoff.mytaskyapp.data.models.AgendaResponse
import com.smithnoff.mytaskyapp.data.remote.TaskyAgendaApi
import com.smithnoff.mytaskyapp.domain.repository.AgendaRepository
import com.smithnoff.mytaskyapp.utils.Resource
import com.smithnoff.mytaskyapp.utils.safeApiCall
import javax.inject.Inject

class AgendaRepositoryImpl @Inject constructor(private val api: TaskyAgendaApi,
private val dao: TaskyTaskDao) : AgendaRepository {

    override suspend fun getAgendaItemsByDay(timezone: String, time: Long) : Resource<AgendaResponse> {
        return safeApiCall { api.getAgenda(timezone,time) }
    }

    override suspend fun getAgendaLocalItemsByDay(
        date: String
    ): List<TaskyTaskEntity> {
        return dao.getAgendaLocalItems(date)
    }
}
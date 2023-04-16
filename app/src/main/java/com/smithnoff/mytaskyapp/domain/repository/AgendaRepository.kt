package com.smithnoff.mytaskyapp.domain.repository

import com.smithnoff.mytaskyapp.data.models.AgendaResponse
import com.smithnoff.mytaskyapp.utils.Resource

interface AgendaRepository {

    suspend fun getAgendaItemsByDay(timezone:String, time:Long): Resource<AgendaResponse>
}
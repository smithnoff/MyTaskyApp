package com.smithnoff.mytaskyapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.smithnoff.mytaskyapp.data.models.AgendaResponse
import com.smithnoff.mytaskyapp.data.repository.AgendaRepositoryImpl
import com.smithnoff.mytaskyapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AgendaHomeViewModel @Inject constructor(private val agendaApi: AgendaRepositoryImpl) :
    ViewModel() {
    private val _agendaItemList = MutableLiveData<Resource<AgendaResponse>>()
    val agendaItemList: LiveData<Resource<AgendaResponse>> = _agendaItemList
    fun getAgendaItems(timeZone: String, time: Long) {

        viewModelScope.launch {
            val response = agendaApi.getAgendaItemsByDay(timeZone, time)
            _agendaItemList.postValue(response)
        }
    }
}
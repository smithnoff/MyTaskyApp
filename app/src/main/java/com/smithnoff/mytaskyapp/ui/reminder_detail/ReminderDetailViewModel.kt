package com.smithnoff.mytaskyapp.ui.reminder_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.smithnoff.mytaskyapp.data.models.TaskyReminder
import com.smithnoff.mytaskyapp.data.repository.ReminderRepositoryImpl
import com.smithnoff.mytaskyapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReminderDetailViewModel @Inject constructor(private val reminderRepository: ReminderRepositoryImpl) :
    ViewModel() {
    private var _reminderTitle = MutableLiveData<String>()
    val reminderTitle : LiveData<String> = _reminderTitle
    private var _reminderDescription = MutableLiveData<String>()
    val reminderDescription : LiveData<String> = _reminderDescription
    private val _createdReminderResult = MutableLiveData<Resource<Unit>>()
    val createdReminderResult: LiveData<Resource<Unit>> = _createdReminderResult



    fun createNewReminder(taskyReminder: TaskyReminder) {
       viewModelScope.launch {
          val response = reminderRepository.createReminder(taskyReminder)
           _createdReminderResult.postValue(response)
       }
    }
    fun setReminderTitle(title: String){
        _reminderTitle.postValue(title)
    }

    fun getTitle(): String {
        return _reminderTitle.value?:""
    }

    fun setReminderDescription(title: String){
        _reminderDescription.postValue(title)
    }

    fun getDescription(): String {
        return _reminderDescription.value?:""
    }
}
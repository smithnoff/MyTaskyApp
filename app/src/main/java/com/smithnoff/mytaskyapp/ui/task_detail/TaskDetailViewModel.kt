package com.smithnoff.mytaskyapp.ui.task_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.smithnoff.mytaskyapp.data.repository.TasksRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskDetailViewModel @Inject constructor(private val tasksRepository: TasksRepositoryImpl) :
    ViewModel() {
    private var _taskTitle = MutableLiveData<String>()
    val taskTitle : LiveData<String> = _taskTitle
    private var _taskDescription = MutableLiveData<String>()
    val taskDescription : LiveData<String> = _taskDescription
    fun createNewTask() {
        viewModelScope.launch {
            //tasksRepository.createTask()
        }
    }

    fun setTaskTitle(title: String){
        _taskTitle.postValue(title)
    }

    fun getTitle(): String {
     return _taskTitle.value?:""
    }

    fun setTaskDescription(title: String){
        _taskDescription.postValue(title)
    }

    fun getTitleDescription(): String {
     return _taskDescription.value?:""
    }
}
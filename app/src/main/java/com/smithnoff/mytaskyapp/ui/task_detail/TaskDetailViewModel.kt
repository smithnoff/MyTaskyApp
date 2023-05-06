package com.smithnoff.mytaskyapp.ui.task_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.smithnoff.mytaskyapp.data.mappers.toDomain
import com.smithnoff.mytaskyapp.data.models.TaskyTask
import com.smithnoff.mytaskyapp.data.repository.TasksRepositoryImpl
import com.smithnoff.mytaskyapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskDetailViewModel @Inject constructor(private val tasksRepository: TasksRepositoryImpl) :
    ViewModel() {
    private val _createdTaskResult = MutableLiveData<Resource<Unit>>()
    val createdTaskResult: LiveData<Resource<Unit>> = _createdTaskResult
    private val _editedTaskResult = MutableLiveData<Resource<Unit>>()
    val editedTaskResult: LiveData<Resource<Unit>> = _editedTaskResult

    private var _taskTitle = MutableLiveData<String>()
    val taskTitle : LiveData<String> = _taskTitle
    private var _taskDescription = MutableLiveData<String>()
    val taskDescription : LiveData<String> = _taskDescription

    private var _screenMode = MutableLiveData<Int?>()

    fun createNewTask(createdTask: TaskyTask) {
        viewModelScope.launch {
            tasksRepository.createLocalTask(createdTask.toDomain())
            _createdTaskResult.postValue(Resource.Success(Unit))
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

    fun updateTask(editedTask: TaskyTask) {
        viewModelScope.launch {
            val response = tasksRepository.createTask(editedTask)
            _editedTaskResult.postValue(response)
        }
    }

    fun setScreenMode(screenMode:Int?) {
        _screenMode.value = screenMode
    }
    fun getScreenMode() = _screenMode.value
}
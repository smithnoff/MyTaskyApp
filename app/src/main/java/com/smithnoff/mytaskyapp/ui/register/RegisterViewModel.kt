package com.smithnoff.mytaskyapp.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.smithnoff.mytaskyapp.data.models.RegisterUserRequest
import com.smithnoff.mytaskyapp.data.repository.AuthRepositoryImpl
import com.smithnoff.mytaskyapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val authRepositoryImpl: AuthRepositoryImpl) : ViewModel() {

    private val _registerUser = MutableLiveData<Resource<Unit>>()
    val registerUser: LiveData<Resource<Unit>> = _registerUser

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading
    fun doRegisterUser(userInfo: RegisterUserRequest){
        viewModelScope.launch {
           val response = authRepositoryImpl.registerUser(userInfo)
            _registerUser.postValue(response)
        }
    }
}
package com.smithnoff.mytaskyapp.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.smithnoff.mytaskyapp.data.models.UserLoginRequest
import com.smithnoff.mytaskyapp.data.models.UserLoginResponse
import com.smithnoff.mytaskyapp.data.repository.AuthRepositoryImpl
import com.smithnoff.mytaskyapp.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private  val userApi: AuthRepositoryImpl) : ViewModel() {
    private val _loggedUserInfo = MutableLiveData<NetworkResult<UserLoginResponse>>()
    val loggedUserInfo: LiveData<NetworkResult<UserLoginResponse>> = _loggedUserInfo
    private val _errorText = MutableLiveData<String>()
    val errorText: LiveData<String> = _errorText

    fun doLogin(email: String, password: String) {
        _loggedUserInfo.postValue(NetworkResult.Loading())

        viewModelScope.launch {
           val response = userApi.loginUser(UserLoginRequest(email,password))
            if (response.data != null){
                _loggedUserInfo.postValue(response)
            }else{
                _loggedUserInfo.postValue(NetworkResult.Loading(false))
                _errorText.postValue(response.message?:"Unknown error.")
            }
        }
    }
}
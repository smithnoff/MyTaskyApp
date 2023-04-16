package com.smithnoff.mytaskyapp.data.remote

import com.smithnoff.mytaskyapp.BuildConfig
import com.smithnoff.mytaskyapp.utils.SessionManagerUtil
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor() : Interceptor {

    @Inject
    lateinit var sessionManager: SessionManagerUtil
    override fun intercept(chain: Interceptor.Chain): Response {
        val url = chain.request().url.toString()
        val request = chain.request().newBuilder()
            .addHeader("x-api-key", BuildConfig.TASKY_API_KEY)
           val excludedEndpoints = listOf("/register","/login","/logout")
        if(excludedEndpoints.none { url.contains(it) }){
            request.addHeader("Authorization", "Bearer ${sessionManager.getUserToken()}")
        }

        return chain.proceed(request.build())
    }
}
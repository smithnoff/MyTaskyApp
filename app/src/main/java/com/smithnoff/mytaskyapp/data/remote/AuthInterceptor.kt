package com.smithnoff.mytaskyapp.data.remote

import com.smithnoff.mytaskyapp.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("x-api-key", BuildConfig.TASKY_API_KEY)
        return chain.proceed(request.build())
    }
}
package com.smithnoff.mytaskyapp.di

import com.smithnoff.mytaskyapp.data.remote.TaskyApi
import com.smithnoff.mytaskyapp.utils.Constants.BASE_URL
import com.smithnoff.mytaskyapp.utils.Constants.BEARER
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideTaskyClient(): OkHttpClient =
        OkHttpClient.Builder().addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("Authorization", BEARER).build()
            chain.proceed(request)
        }.build()

    @Provides
    @Singleton
    fun provideRetrofit(client:OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideTaskyApi(retrofit:Retrofit): TaskyApi =
        retrofit.create(TaskyApi::class.java)

}

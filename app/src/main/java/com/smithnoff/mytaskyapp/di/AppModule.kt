package com.smithnoff.mytaskyapp.di

import com.smithnoff.mytaskyapp.data.remote.AuthInterceptor
import com.smithnoff.mytaskyapp.data.remote.TaskyApi
import com.smithnoff.mytaskyapp.domain.validators.UserValidator
import com.smithnoff.mytaskyapp.utils.Constants.BASE_URL
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
    fun provideTaskyClient(authInterceptor: AuthInterceptor): OkHttpClient =
        OkHttpClient.Builder().addInterceptor(authInterceptor).build()

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideTaskyApi(retrofit: Retrofit): TaskyApi =
        retrofit.create(TaskyApi::class.java)

    @Provides
    @Singleton
    fun provideUserValidator(): UserValidator = UserValidator()

}

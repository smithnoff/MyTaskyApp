package com.smithnoff.mytaskyapp.di

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.smithnoff.mytaskyapp.data.models.TaskyTask
import com.smithnoff.mytaskyapp.data.remote.*
import com.smithnoff.mytaskyapp.domain.validators.UserValidator
import com.smithnoff.mytaskyapp.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
    fun provideTaskyAuthApi(retrofit: Retrofit): TaskyAuthApi =
        retrofit.create(TaskyAuthApi::class.java)

    @Provides
    @Singleton
    fun provideUserValidator(): UserValidator = UserValidator()

    @Provides
    @Singleton
    fun provideSecureSharedPreferences(@ApplicationContext appContext: Context): SharedPreferences {
        val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
        return EncryptedSharedPreferences.create(
            "PreferencesFilename",
            masterKeyAlias,
            appContext,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    @Provides
    @Singleton
    fun provideTaskyAgendaApi(retrofit: Retrofit): TaskyAgendaApi =
        retrofit.create(TaskyAgendaApi::class.java)

    @Provides
    @Singleton
    fun provideTaskyTaskApi(retrofit: Retrofit): TaskyTaskApi =
        retrofit.create(TaskyTaskApi::class.java)

    @Provides
    @Singleton
    fun provideTaskyReminderApi(retrofit: Retrofit): TaskyReminderApi =
        retrofit.create(TaskyReminderApi::class.java)
}

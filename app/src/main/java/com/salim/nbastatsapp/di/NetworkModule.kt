package com.salim.nbastatsapp.di

import com.salim.nbastatsapp.player.PlayerApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(URL).addConverterFactory(MoshiConverterFactory.create()).build()
    }

    @Singleton
    @Provides
    fun providePlayerApiService(retrofit: Retrofit): PlayerApiService {
        return retrofit.create(PlayerApiService::class.java)
    }

    private const val URL = "https://www.balldontlie.io/api/v1"
}
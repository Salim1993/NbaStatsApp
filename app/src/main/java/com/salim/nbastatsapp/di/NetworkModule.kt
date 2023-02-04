package com.salim.nbastatsapp.di

import com.salim.nbastatsapp.network.NbaStatsApiService
import com.salim.nbastatsapp.player.PlayerTypeAdapter
import com.serjltt.moshi.adapters.Wrapped
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
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
    fun provideMoshi(): Moshi {
        val initialMoshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        return Moshi.Builder()
            .add(PlayerTypeAdapter(initialMoshi))
            .add(Wrapped.ADAPTER_FACTORY)
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun providesRetrofit(moshi: Moshi): Retrofit {
        return Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Singleton
    @Provides
    fun providePlayerApiService(retrofit: Retrofit): NbaStatsApiService {
        return retrofit.create(NbaStatsApiService::class.java)
    }

    private const val URL = "https://www.balldontlie.io/api/v1/"
}
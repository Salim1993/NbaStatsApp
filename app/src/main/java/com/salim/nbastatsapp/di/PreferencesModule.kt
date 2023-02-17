package com.salim.nbastatsapp.di

import android.content.Context
import com.salim.nbastatsapp.utilities.SharedPreferencesWrapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object PreferencesModule {

    private const val NBA_STATS_PREF = "NBA_STATS_PREF"

    @Singleton
    @Provides
    fun providePreferences(@ApplicationContext appContext: Context): SharedPreferencesWrapper {
        val prefs = appContext.getSharedPreferences(NBA_STATS_PREF, Context.MODE_PRIVATE)
        return SharedPreferencesWrapper(prefs)
    }
}

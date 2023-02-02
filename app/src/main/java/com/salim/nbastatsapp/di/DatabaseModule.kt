package com.salim.nbastatsapp.di

import android.content.Context
import androidx.room.Room
import com.salim.nbastatsapp.database.NbaAppDatabase
import com.salim.nbastatsapp.player.PlayerDao
import com.salim.nbastatsapp.team.TeamDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): NbaAppDatabase {
        return Room.databaseBuilder(
            context = context,
            klass = NbaAppDatabase::class.java,
            name = NbaAppDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    fun providePlayerDao(database: NbaAppDatabase): PlayerDao {
        return database.playerDao()
    }

    @Provides
    fun provideTeamDao(database: NbaAppDatabase): TeamDao {
        return database.teamDao()
    }
}
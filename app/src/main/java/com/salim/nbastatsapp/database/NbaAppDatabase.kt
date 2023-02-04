package com.salim.nbastatsapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.salim.nbastatsapp.team.Team
import com.salim.nbastatsapp.player.Player
import com.salim.nbastatsapp.player.PlayerDao
import com.salim.nbastatsapp.team.TeamDao

@Database(entities = [Player::class, Team::class], version = 1)
abstract class NbaAppDatabase : RoomDatabase() {

    abstract fun playerDao(): PlayerDao
    abstract fun teamDao(): TeamDao

    companion object {
        const val DATABASE_NAME = "nba-app-database"
    }
}
package com.salim.nbastatsapp.player

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import androidx.room.Query

@Dao
interface PlayerDao {

    @Insert
    suspend fun insertPlayer(player: Player)

    @Update
    suspend fun updatePlayer(player: Player)

    @Delete
    suspend fun deletePlayer(player: Player)

    @Query("SELECT * FROM players")
    suspend fun getAllPlayers(): List<Player>

    @Query("SELECT * FROM players WHERE id = :id")
    suspend fun getPlayerById(id: Int): Player
}

package com.salim.nbastatsapp.player

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Transaction

import kotlinx.coroutines.flow.Flow

@Dao
interface PlayerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlayer(player: Player)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlayerList(list: List<Player>)

    @Update
    suspend fun updatePlayer(player: Player)

    @Delete
    suspend fun deletePlayer(player: Player)

    @Query("SELECT * FROM players")
    fun getAllPlayers(): Flow<List<Player>>

    @Query("SELECT * FROM players WHERE id = :id")
    fun getPlayerById(id: Int): Flow<Player>

    @Query("SELECT * FROM players WHERE id = :id")
    suspend fun getPlayerByIdSynchronous(id: Int): Player

    @Transaction
    @Query("SELECT * FROM players")
    fun getAllPlayersWithTeam(): Flow<List<PlayerAndTeam>>
}

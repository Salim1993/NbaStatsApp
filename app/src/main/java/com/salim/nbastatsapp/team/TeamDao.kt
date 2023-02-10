package com.salim.nbastatsapp.team

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import androidx.room.Delete
import androidx.room.Query
import com.salim.nbastatsapp.player.Player
import kotlinx.coroutines.flow.Flow

@Dao
interface TeamDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTeam(team: Team)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllTeams(teams: List<Team>)

    @Update
    suspend fun updateTeam(team: Team)

    @Delete
    suspend fun deleteTeam(team: Team)

    @Query("SELECT * FROM teams")
    fun getAllTeams(): Flow<List<Team>>

    @Query("SELECT * FROM teams WHERE id = :id")
    fun getTeamById(id: Int): Flow<Team>

    @Query("SELECT * FROM teams WHERE id = :id")
    suspend fun getTeamByIdSynchronous(id: Int): Team
}

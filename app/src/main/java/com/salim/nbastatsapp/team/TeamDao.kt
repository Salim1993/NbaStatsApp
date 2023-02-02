package com.salim.nbastatsapp.team

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import androidx.room.Delete
import androidx.room.Query
import com.salim.nbastatsapp.Team

@Dao
interface TeamDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTeam(team: Team)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllTeams(teams: List<Team>)

    @Update
    fun updateTeam(team: Team)

    @Delete
    fun deleteTeam(team: Team)

    @Query("SELECT * FROM teams")
    fun getAllTeams(): List<Team>

    @Query("SELECT * FROM teams WHERE id = :id")
    fun getTeamById(id: Int): Team
}

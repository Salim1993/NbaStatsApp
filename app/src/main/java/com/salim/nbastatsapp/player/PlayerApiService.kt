package com.salim.nbastatsapp.player

import com.salim.nbastatsapp.team.Team
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * An interface that defines the API calls to retrieve different NBA info objects.
 */
interface PlayerApiService {
    /**
     * Retrieves a list of [Player] objects from the API.
     *
     * @return A list of [Player] objects.
     */
    @GET("players")
    suspend fun getPlayers(): List<Player>

    /**
     * Retrieves info of [Player] objects from the API.
     *
     * @return [Player] object.
     */
    @GET("players/{id}")
    suspend fun getPlayerInfo(@Path("id") id: Int): Player

    /**
     * Retrieves a list of [Team] objects from the API.
     *
     * @return A list of [Team] objects.
     */
    @GET("teams")
    suspend fun getTeams(): List<Team>
}
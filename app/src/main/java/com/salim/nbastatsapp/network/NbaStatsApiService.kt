package com.salim.nbastatsapp.network

import com.salim.nbastatsapp.player.Player
import com.salim.nbastatsapp.team.Team
import com.serjltt.moshi.adapters.Wrapped
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * An interface that defines the API calls to retrieve different NBA info objects.
 */
interface NbaStatsApiService {
    /**
     * Retrieves a list of [Player] objects from the API.
     *
     * @return A list of [Player] objects.
     */
    @GET("players")
    @Wrapped(path = ["data"])
    suspend fun getPlayers(@Query("page") page: Int): List<Player>

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
    @Wrapped(path = ["data"])
    suspend fun getTeams(): List<Team>

    /**
     * Retrieves info of [Team] objects from the API.
     *
     * @return [Team] object.
     */
    @GET("teams/{id}")
    suspend fun getTeamInfo(@Path("id") id: Int): Team
}
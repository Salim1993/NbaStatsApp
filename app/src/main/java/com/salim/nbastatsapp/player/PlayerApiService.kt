package com.salim.nbastatsapp.player

import retrofit2.http.GET

/**
 * An interface that defines the API calls to retrieve [Player] objects.
 */
interface PlayerApiService {
    /**
     * Retrieves a list of [Player] objects from the API.
     *
     * @return A list of [Player] objects.
     */
    @GET("players")
    suspend fun getPlayers(): List<Player>
}
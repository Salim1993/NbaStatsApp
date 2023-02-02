package com.salim.nbastatsapp.player

import javax.inject.Inject


/**
 * A repository class that uses Retrofit to make API calls and retrieve [Player] objects.
 */
class PlayerRepository @Inject constructor(
    private val playerApiService: PlayerApiService,
    private val playerDao: PlayerDao
) {

    /**
     * Makes a call to the API to retrieve a list of [Player] objects.
     *
     * @return A list of [Player] objects.
     */
    suspend fun getPlayers(): List<Player> {
        return playerApiService.getPlayers()
    }
}

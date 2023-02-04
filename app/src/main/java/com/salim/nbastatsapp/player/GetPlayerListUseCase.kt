package com.salim.nbastatsapp.player

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import timber.log.Timber
import java.net.UnknownHostException
import javax.inject.Inject


/**
 * A a use case or repository class that uses Retrofit to make API calls and retrieve list of [Player] .
 */
class GetPlayerListUseCase @Inject constructor(
    private val playerApiService: PlayerApiService,
    private val playerDao: PlayerDao
) {

    val playerListFlow = playerDao.getAllPlayers()

    /**
     * Makes a call to the API to retrieve a list of [Player] objects.
     *
     * @return A list of [Player] objects.
     */
    suspend fun getPlayers(){
        try {
            withContext(Dispatchers.IO) {
                val players = playerApiService.getPlayers()
                playerDao.insertPlayerList(players)
            }
        } catch (e: UnknownHostException) {
            Timber.e("Got unknown exception in getPlayersApi")
        } catch (e: HttpException) {
            Timber.e("Got http exception in getPlayersApi. Http Status: ${e.code()} - Http Message: ${e.message()}")
        }
    }

    suspend fun getPlayerInfo(id: Int){
        try {
            withContext(Dispatchers.IO) {
                val player = playerApiService.getPlayerInfo(id)
                playerDao.insertPlayer(player)
            }
        } catch (e: UnknownHostException) {
            Timber.e("Got unknown exception in getPlayersApi")
        } catch (e: HttpException) {
            Timber.e("Got http exception in getPlayersApi. Http Status: ${e.code()} - Http Message: ${e.message()}")
        }
    }
}

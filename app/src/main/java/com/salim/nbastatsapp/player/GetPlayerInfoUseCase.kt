package com.salim.nbastatsapp.player

import com.salim.nbastatsapp.network.NbaStatsApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import timber.log.Timber
import java.net.UnknownHostException
import javax.inject.Inject


/**
 * A a use case or repository class that uses Retrofit to make API calls and retrieve info of [Player] .
 */
class GetPlayerInfoUseCase @Inject constructor(
    private val nbaStatsApiService: NbaStatsApiService,
    private val playerDao: PlayerDao
) {

    /**
     * Makes a call to the API to retrieve a info of a [Player] object.
     *
     * @return [Player] object.
     */
    suspend fun getPlayerInfo(id: Int){
        try {
            withContext(Dispatchers.IO) {
                val players = nbaStatsApiService.getPlayers()
                playerDao.insertPlayerList(players)
            }
        } catch (e: UnknownHostException) {
            Timber.e("Got unknown exception in getPlayersApi")
        } catch (e: HttpException) {
            Timber.e("Got http exception in getPlayersApi. Http Status: ${e.code()} - Http Message: ${e.message()}")
        }
    }
}

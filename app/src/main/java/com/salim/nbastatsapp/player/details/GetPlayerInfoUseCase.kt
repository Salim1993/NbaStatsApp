package com.salim.nbastatsapp.player.details

import com.salim.nbastatsapp.network.NbaStatsApiService
import com.salim.nbastatsapp.player.Player
import com.salim.nbastatsapp.player.PlayerDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
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

    private val _playerFlow = MutableSharedFlow<Player>()
    val playerFlow = _playerFlow.asSharedFlow()

    /**
     * Makes a call to the API to retrieve a info of a [Player] object.
     *
     * @return [Player] object.
     */
    suspend fun getPlayerInfo(id: Int) {
        withContext(Dispatchers.IO) {
            var player = playerDao.getPlayerByIdSynchronous(id)
            try {
                player = nbaStatsApiService.getPlayerInfo(id)
                playerDao.insertPlayer(player)
            } catch (e: UnknownHostException) {
                Timber.e("Got unknown exception in getPlayersApi")
            } catch (e: HttpException) {
                Timber.e("Got http exception in getPlayersApi. Http Status: ${e.code()} - Http Message: ${e.message()}")
            }
            _playerFlow.emit(player)
        }
    }
}

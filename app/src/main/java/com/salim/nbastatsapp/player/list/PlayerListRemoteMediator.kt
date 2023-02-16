package com.salim.nbastatsapp.player.list

import androidx.paging.*
import com.salim.nbastatsapp.database.NbaAppDatabase
import com.salim.nbastatsapp.network.NbaStatsApiService
import com.salim.nbastatsapp.player.Player
import com.salim.nbastatsapp.player.PlayerDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException
import java.net.UnknownHostException
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class PlayerListRemoteMediator @Inject constructor(
    private val nbaStatsApiService: NbaStatsApiService,
    private val playerDao: PlayerDao,
    //private val nbaAppDatabase: NbaAppDatabase
): RemoteMediator<Int, Player>() {

    var currentPage = FIRST_PAGE_LOAD_KEY

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Player>
    ): MediatorResult {
        try {
            currentPage = when (loadType) {
                LoadType.REFRESH -> FIRST_PAGE_LOAD_KEY
                LoadType.PREPEND ->
                    return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    currentPage + 1
                }
            }

            // get api form network
            val players: List<Player> = withContext(Dispatchers.IO) {
                val players = nbaStatsApiService.getPlayers(currentPage)
                Timber.d("getting more players from api: $players")
                playerDao.insertPlayerList(players)
                return@withContext players
            }
            return MediatorResult.Success(
                endOfPaginationReached = players.isEmpty()
            )
        } catch (e: UnknownHostException) {
            Timber.e("Got unknown host exception in getPlayersApi")
            return MediatorResult.Error(e)
        } catch (e: HttpException) {
            Timber.e("Got http exception in getPlayersApi. Http Status: ${e.code()} - Http Message: ${e.message()}")
            return MediatorResult.Error(e)
        } catch (e: IOException) {
            Timber.e("Got IO exception in getPlayersApi")
            return MediatorResult.Error(e)
        }
    }

    /* TODO: need to figure out how to save time last update in database, for check below
    override suspend fun initialize(): InitializeAction {
        val cacheTimeout = TimeUnit.MILLISECONDS.convert(1, TimeUnit.HOURS)
        return if (System.currentTimeMillis() - nbaAppDatabase.() <= cacheTimeout)
        {
            // Cached data is up-to-date, so there is no need to re-fetch
            // from the network.
            InitializeAction.SKIP_INITIAL_REFRESH
        } else {
            // Need to refresh cached data from network; returning
            // LAUNCH_INITIAL_REFRESH here will also block RemoteMediator's
            // APPEND and PREPEND from running until REFRESH succeeds.
            InitializeAction.LAUNCH_INITIAL_REFRESH
        }
    }
     */

    companion object {
        private const val FIRST_PAGE_LOAD_KEY = 0
        const val PLAYER_PAGING_SIZE = 25
    }
}
package com.salim.nbastatsapp.player.list

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.salim.nbastatsapp.network.NbaStatsApiService
import com.salim.nbastatsapp.player.Player
import com.salim.nbastatsapp.player.PlayerDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import timber.log.Timber
import java.net.UnknownHostException
import javax.inject.Inject


/**
 * A a use case or repository class that uses Retrofit to make API calls and retrieve list of [Player] .
 */
class PlayerPagingSource @Inject constructor(
    private val nbaStatsApiService: NbaStatsApiService,
    private val playerDao: PlayerDao
): PagingSource<Int, Player>() {

    val playerListFlow = playerDao.getAllPlayersWithTeam()

    /**
     * The refresh key is used for subsequent calls to PagingSource.Load after the initial load.
     */
    override fun getRefreshKey(state: PagingState<Int, Player>): Int? {
        // We need to get the previous key (or next key if previous is null) of the page
        // that was closest to the most recently accessed index.
        // Anchor position is the most recently accessed index.
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    @Suppress("ReturnCount")
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Player> {
        val pageIndex = params.key ?: STARTING_PAGE_INDEX
        try {
            val players: List<Player> = withContext(Dispatchers.IO) {
                val players = nbaStatsApiService.getPlayers(pageIndex)
                playerDao.insertPlayerList(players)
                return@withContext players
            }
            val nextKey = if (players.isEmpty()) {
                null
            } else {
                pageIndex + 1
            }
            return LoadResult.Page(
                data = players,
                prevKey = null,
                nextKey = nextKey
            )
        } catch (e: UnknownHostException) {
            Timber.e("Got unknown exception in getPlayersApi")
            return LoadResult.Error(e)
        } catch (e: HttpException) {
            Timber.e("Got http exception in getPlayersApi. Http Status: ${e.code()} - Http Message: ${e.message()}")
            return LoadResult.Error(e)
        }
    }

    companion object {
        private const val STARTING_PAGE_INDEX = 0
        const val PLAYER_PAGING_SIZE = 25
    }
}

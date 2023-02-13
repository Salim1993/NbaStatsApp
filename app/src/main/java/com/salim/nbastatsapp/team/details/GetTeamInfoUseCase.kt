package com.salim.nbastatsapp.team.details

import com.salim.nbastatsapp.network.NbaStatsApiService
import com.salim.nbastatsapp.team.Team
import com.salim.nbastatsapp.team.TeamDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import timber.log.Timber
import java.net.UnknownHostException
import javax.inject.Inject


/**
 * A a use case or repository class that uses Retrofit to make API calls and retrieve info of [Team] .
 */
class GetTeamInfoUseCase @Inject constructor(
    private val nbaStatsApiService: NbaStatsApiService,
    private val teamDao: TeamDao
) {

    private val _teamFlow = MutableSharedFlow<Team>()
    val teamFlow = _teamFlow.asSharedFlow()

    /**
     * Makes a call to the API to retrieve a info of a [Team] object.
     *
     * @return [Team] object.
     */
    suspend fun getTeamInfo(id: Int) {
        withContext(Dispatchers.IO) {
            var player = teamDao.getTeamByIdSynchronous(id)
            try {
                player = nbaStatsApiService.getTeamInfo(id)
                teamDao.insertTeam(player)
            } catch (e: UnknownHostException) {
                Timber.e("Got unknown exception in getTeamINfoApi")
            } catch (e: HttpException) {
                Timber.e(
                    "Got http exception in getTeamInfoApi. Http Status: ${e.code()} - Http Message: ${e.message()}"
                )
            }
            _teamFlow.emit(player)
        }
    }
}

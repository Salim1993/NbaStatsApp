package com.salim.nbastatsapp.team.details

import com.salim.nbastatsapp.network.NbaStatsApiService
import com.salim.nbastatsapp.team.Team
import com.salim.nbastatsapp.team.TeamDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
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

    private val _teamFlow = MutableStateFlow(Team.returnEmptyTeam())
    val teamFlow = _teamFlow.asStateFlow()

    /**
     * Makes a call to the API to retrieve a info of a [Team] object.
     *
     * @return [Team] object.
     */
    suspend fun getTeamInfo(id: Int) {
        withContext(Dispatchers.IO) {
            var team = teamDao.getTeamByIdSynchronous(id)
            try {
                team = nbaStatsApiService.getTeamInfo(id)
                teamDao.insertTeam(team)
            } catch (e: UnknownHostException) {
                Timber.e("Got unknown exception in getTeamINfoApi")
            } catch (e: HttpException) {
                Timber.e(
                    "Got http exception in getTeamInfoApi. Http Status: ${e.code()} - Http Message: ${e.message()}"
                )
            }
            _teamFlow.emit(team)
        }
    }

    suspend fun getTeamInfoUsingName(name: String) {
        withContext(Dispatchers.IO) {
            var team = teamDao.getTeamByNameSynchronous(name)
            try {
                team = nbaStatsApiService.getTeamInfo(team.id)
                teamDao.insertTeam(team)
            } catch (e: UnknownHostException) {
                Timber.e("Got unknown exception in getTeamINfoApi")
            } catch (e: HttpException) {
                Timber.e(
                    "Got http exception in getTeamInfoApi. Http Status: ${e.code()} - Http Message: ${e.message()}"
                )
            }
            _teamFlow.emit(team)
        }
    }
}

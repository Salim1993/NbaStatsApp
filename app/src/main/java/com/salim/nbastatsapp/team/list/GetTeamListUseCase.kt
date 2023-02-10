package com.salim.nbastatsapp.team.list

import com.salim.nbastatsapp.network.NbaStatsApiService
import com.salim.nbastatsapp.team.TeamDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import timber.log.Timber
import java.net.UnknownHostException
import javax.inject.Inject


/**
 * A a use case or repository class that uses Retrofit to make API calls and retrieve list of [Team] .
 */
class GetTeamListUseCase @Inject constructor(
    private val nbaStatsApiService: NbaStatsApiService,
    private val teamDao: TeamDao
) {

    val teamListFlow = teamDao.getAllTeams()

    /**
     * Makes a call to the API to retrieve a list of [Team] objects.
     *
     * @return A list of [Team] objects.
     */
    suspend fun getTeams(){
        try {
            withContext(Dispatchers.IO) {
                val teams = nbaStatsApiService.getTeams()
                teamDao.insertAllTeams(teams)
            }
        } catch (e: UnknownHostException) {
            Timber.e("Got unknown exception in getTeamsApi")
        } catch (e: HttpException) {
            Timber.e("Got http exception in getTeamsApi. Http Status: ${e.code()} - Http Message: ${e.message()}")
        }
    }
}

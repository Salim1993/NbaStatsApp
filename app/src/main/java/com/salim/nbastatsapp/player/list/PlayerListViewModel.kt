package com.salim.nbastatsapp.player.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.salim.nbastatsapp.network.NbaStatsApiService
import com.salim.nbastatsapp.player.PlayerDao
import com.salim.nbastatsapp.team.list.GetTeamListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PlayerListViewModel @Inject constructor(
    private val getTeamListUseCase: GetTeamListUseCase,
    private val nbaStatsApiService: NbaStatsApiService,
    private val playerDao: PlayerDao
): ViewModel() {

    //val playerList = getPlayerListUseCase.playerListFlow
    val playerListFlow = Pager(
        PagingConfig(pageSize = PlayerPagingSource.PLAYER_PAGING_SIZE)
    ) {
        PlayerPagingSource(nbaStatsApiService, playerDao)
    }.flow.cachedIn(viewModelScope)

    init {
        getTeamList()
    }

    private fun getTeamList() = viewModelScope.launch {
        getTeamListUseCase.getTeams()
    }
}
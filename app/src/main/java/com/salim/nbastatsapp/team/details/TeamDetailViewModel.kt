package com.salim.nbastatsapp.team.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.salim.nbastatsapp.navigation.TeamDetailsNavigationInfo
import com.salim.nbastatsapp.player.Player
import com.salim.nbastatsapp.player.PlayerDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class TeamDetailViewModel @Inject constructor(
    private val getTeamInfoUseCase: GetTeamInfoUseCase,
    private val playerDao: PlayerDao,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    val teamFlow = getTeamInfoUseCase.teamFlow

    private val _playerListFlow = MutableSharedFlow<List<Player>>()
    val playerListFlow = _playerListFlow.asSharedFlow()

    val id = savedStateHandle.get<Int>(TeamDetailsNavigationInfo.TEAM_DETAIL_DESTINATION_ARG)

    init {
        setTeamInfoCollection()
        getTeamInfo()
    }

    private fun getTeamInfo() = viewModelScope.launch {
        if (id != null) {
            getTeamInfoUseCase.getTeamInfo(id)
        }
    }

    private fun setTeamInfoCollection() = viewModelScope.launch {
        teamFlow.collect {
            getListOfPlayersFromTeam(it.fullName)
        }
    }

    private fun getListOfPlayersFromTeam(fullName: String) = viewModelScope.launch {
        val playerList = playerDao.getPlayersListFromTeamNameSynchronous(fullName)
        _playerListFlow.emit(playerList)
    }
}
package com.salim.nbastatsapp.player.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.salim.nbastatsapp.team.GetTeamListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PlayerListViewModel @Inject constructor(
    private val getPlayerListUseCase: GetPlayerListUseCase,
    private val getTeamListUseCase: GetTeamListUseCase
): ViewModel() {

    val playerList = getPlayerListUseCase.playerListFlow

    init {
        getPlayerList()
        getTeamList()
    }

    private fun getPlayerList() = viewModelScope.launch {
        getPlayerListUseCase.getPlayers()
    }

    private fun getTeamList() = viewModelScope.launch {
        getTeamListUseCase.getTeams()
    }
}
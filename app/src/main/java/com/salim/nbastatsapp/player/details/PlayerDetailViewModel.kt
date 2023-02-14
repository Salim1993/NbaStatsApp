package com.salim.nbastatsapp.player.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.salim.nbastatsapp.navigation.PlayerDetailsNavigationInfo
import com.salim.nbastatsapp.team.details.GetTeamInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PlayerDetailViewModel @Inject constructor(
    private val getPlayerInfoUseCase: GetPlayerInfoUseCase,
    private val getTeamInfoUseCase: GetTeamInfoUseCase,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    val playerFlow = getPlayerInfoUseCase.playerFlow
    val teamFlow = getTeamInfoUseCase.teamFlow

    val id = savedStateHandle.get<Int>(PlayerDetailsNavigationInfo.PLAYER_DETAIL_DESTINATION_ARG)

    init {
        getPlayerList()
        setupPlayerCollection()
    }

    private fun getPlayerList() = viewModelScope.launch {
        if (id != null) {
            getPlayerInfoUseCase.getPlayerInfo(id)
        }
    }

    private fun setupPlayerCollection() = viewModelScope.launch {
        playerFlow.collect {
            if(it.teamName.isNotEmpty()) {
                getTeamInfoUseCase.getTeamInfoUsingName(it.teamName)
            }
        }
    }
}
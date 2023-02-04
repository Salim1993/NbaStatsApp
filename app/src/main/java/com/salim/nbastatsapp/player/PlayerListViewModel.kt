package com.salim.nbastatsapp.player

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PlayerListViewModel @Inject constructor(
    private val getPlayerListUseCase: GetPlayerListUseCase
): ViewModel() {

    val playerList = getPlayerListUseCase.playerListFlow

    init {
        getPlayerList()
    }

    private fun getPlayerList() = viewModelScope.launch {
        getPlayerListUseCase.getPlayers()
    }
}
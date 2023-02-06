package com.salim.nbastatsapp.player.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.salim.nbastatsapp.navigation.PlayerDetailsNavigationInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PlayerDetailViewModel @Inject constructor(
    private val getPlayerInfoUseCase: GetPlayerInfoUseCase,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    val playerFlow = getPlayerInfoUseCase.playerFlow

    val id = savedStateHandle.get<Int>(PlayerDetailsNavigationInfo.PLAYER_DETAIL_DESTINATION_ARG)

    init {
        getPlayerList()
    }

    private fun getPlayerList() = viewModelScope.launch {
        if (id != null) {
            getPlayerInfoUseCase.getPlayerInfo(id)
        }
    }
}
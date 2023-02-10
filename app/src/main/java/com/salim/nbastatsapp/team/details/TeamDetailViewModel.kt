package com.salim.nbastatsapp.team.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.salim.nbastatsapp.navigation.PlayerDetailsNavigationInfo
import com.salim.nbastatsapp.navigation.TeamDetailsNavigationInfo
import com.salim.nbastatsapp.navigation.TeamsListNavigationInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class TeamDetailViewModel @Inject constructor(
    private val getTeamInfoUseCase: GetTeamInfoUseCase,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    val teamFlow = getTeamInfoUseCase.teamFlow

    val id = savedStateHandle.get<Int>(TeamDetailsNavigationInfo.TEAM_DETAIL_DESTINATION_ARG)

    init {
        getTeamInfo()
    }

    private fun getTeamInfo() = viewModelScope.launch {
        if (id != null) {
            getTeamInfoUseCase.getTeamInfo(id)
        }
    }
}
package com.salim.nbastatsapp.team.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class TeamListViewModel @Inject constructor(
    private val getTeamListUseCase: GetTeamListUseCase
): ViewModel() {

    val teamList = getTeamListUseCase.teamListFlow

    init {
        getTeamList()
    }

    private fun getTeamList() = viewModelScope.launch {
        getTeamListUseCase.getTeams()
    }
}
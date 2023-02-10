package com.salim.nbastatsapp.ui

import com.salim.nbastatsapp.R
import com.salim.nbastatsapp.navigation.PlayerListNavigationInfo
import com.salim.nbastatsapp.navigation.TeamsListNavigationInfo

sealed class BottomNavigationItems(var title: Int, var icon: Int, var route: String) {

    object PlayerList: BottomNavigationItems(
        title = R.string.player_list,
        icon = R.drawable.player_list_icon,
        route = PlayerListNavigationInfo.PLAYER_LIST_DESTINATION_ROUTE
    )

    object TeamList: BottomNavigationItems(
        title = R.string.teams,
        icon = R.drawable.teams_icon,
        route = TeamsListNavigationInfo.TEAMS_LIST_DESTINATION_ROUTE
    )
}

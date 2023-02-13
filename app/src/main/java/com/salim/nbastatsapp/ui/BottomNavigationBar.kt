package com.salim.nbastatsapp.ui

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.salim.nbastatsapp.navigation.PlayerListNavigationInfo
import com.salim.nbastatsapp.navigation.TeamsListNavigationInfo
import com.salim.nbastatsapp.navigation.navigateSingleTopTo

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val items = listOf(BottomNavigationItems.PlayerList, BottomNavigationItems.TeamList)
    var selectedItem by rememberSaveable { mutableStateOf(0) }

    // Added so that back pressing to routes on the bottom bar, will switch active bottom bar selection
    navController.addOnDestinationChangedListener { _, destination, _ ->
        val currentRoute = destination.route
        if (currentRoute == PlayerListNavigationInfo.PLAYER_LIST_DESTINATION_ROUTE) {
            selectedItem = items.indexOfFirst { it.route == PlayerListNavigationInfo.PLAYER_LIST_DESTINATION_ROUTE }
        } else if (currentRoute == TeamsListNavigationInfo.TEAMS_LIST_DESTINATION_ROUTE) {
            selectedItem = items.indexOfFirst { it.route == TeamsListNavigationInfo.TEAMS_LIST_DESTINATION_ROUTE }
        }
    }

    NavigationBar {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = stringResource(id = item.title)
                    )},
                label = { Text(text = stringResource(id = item.title)) },
                selected = selectedItem == index,
                onClick = {
                    selectedItem = index
                    when(item) {
                        BottomNavigationItems.PlayerList -> navController.navigateSingleTopTo(
                            PlayerListNavigationInfo.PLAYER_LIST_DESTINATION_ROUTE
                        )
                        BottomNavigationItems.TeamList ->  navController.navigateSingleTopTo(
                            TeamsListNavigationInfo.TEAMS_LIST_DESTINATION_ROUTE
                        )
                    }
                }
            )
        }
    }
}
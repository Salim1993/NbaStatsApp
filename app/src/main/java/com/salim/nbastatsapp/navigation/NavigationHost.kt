package com.salim.nbastatsapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.salim.nbastatsapp.player.details.PlayerDetailScreen
import com.salim.nbastatsapp.player.list.PlayerListScreen

@Composable
fun NavigationHost(
    modifier: Modifier,
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        modifier = modifier,
        startDestination = PlayerListNavigationInfo.PLAYER_LIST_DESTINATION_ROUTE
    ) {
        composable(route = PlayerListNavigationInfo.PLAYER_LIST_DESTINATION_ROUTE) {
            PlayerListScreen(modifier = modifier)
        }
        composable(PlayerDetailsNavigationInfo.PLAYER_DETAIL_DESTINATION_ROUTE) {
            PlayerDetailScreen(modifier = modifier)
        }
    }
}
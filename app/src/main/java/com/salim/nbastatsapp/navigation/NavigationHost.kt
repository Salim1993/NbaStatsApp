package com.salim.nbastatsapp.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.salim.nbastatsapp.player.details.PlayerDetailScreen
import com.salim.nbastatsapp.player.details.PlayerDetailViewModel
import com.salim.nbastatsapp.player.list.PlayerListScreen
import com.salim.nbastatsapp.player.list.PlayerListViewModel
import com.salim.nbastatsapp.team.list.TeamListScreen
import com.salim.nbastatsapp.team.list.TeamListViewModel

@Composable
fun NavigationHost(
    modifier: Modifier,
    navController: NavHostController,
    scaffoldPadding: PaddingValues
) {
    NavHost(
        navController = navController,
        modifier = modifier.padding(bottom = scaffoldPadding.calculateBottomPadding()),
        startDestination = PlayerListNavigationInfo.PLAYER_LIST_DESTINATION_ROUTE
    ) {
        composable(route = PlayerListNavigationInfo.PLAYER_LIST_DESTINATION_ROUTE) {
            val viewModel = hiltViewModel<PlayerListViewModel>()
            PlayerListScreen(
                modifier = modifier,
                playerListViewModel = viewModel,
                onClickPlayer = {
                    navController.navigateSingleTopTo(PlayerDetailsNavigationInfo.buildPlayerDetailRoute(it))
                }
            )
        }
        composable(
            route = PlayerDetailsNavigationInfo.PLAYER_DETAIL_DESTINATION_ROUTE,
            arguments = listOf(navArgument("player_name") { type = NavType.IntType })
        ) {
            val viewModel = hiltViewModel<PlayerDetailViewModel>()
            PlayerDetailScreen(modifier = modifier, playerDetailViewModel = viewModel)
        }
        composable(route = TeamsListNavigationInfo.TEAMS_LIST_DESTINATION_ROUTE) {
            val viewModel = hiltViewModel<TeamListViewModel>()
            TeamListScreen(
                modifier = modifier,
                teamListViewModel = viewModel,
                onClickTeam = {
                    // TODO: replace below with correct navigation
                    //navController.navigateSingleTopTo(PlayerDetailsNavigationInfo.buildPlayerDetailRoute(it))
                }
            )
        }
    }
}

fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) {
        popUpTo(
            this@navigateSingleTopTo.graph.findStartDestination().id
        ) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
package com.salim.nbastatsapp.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.salim.nbastatsapp.player.details.PlayerDetailScreen
import com.salim.nbastatsapp.player.details.PlayerDetailViewModel
import com.salim.nbastatsapp.player.list.PlayerListScreen
import com.salim.nbastatsapp.player.list.PlayerListViewModel
import com.salim.nbastatsapp.team.details.TeamDetailScreen
import com.salim.nbastatsapp.team.details.TeamDetailViewModel
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
            arguments = listOf(navArgument(PlayerDetailsNavigationInfo.PLAYER_DETAIL_DESTINATION_ARG) {
                type = NavType.IntType
            })
        ) {
            val viewModel = hiltViewModel<PlayerDetailViewModel>()
            PlayerDetailScreen(
                modifier = modifier,
                playerDetailViewModel = viewModel,
                teamOnClick = {
                    navController.navigateSingleTopTo(TeamDetailsNavigationInfo.buildTeamDetailRoute(it))
                }
            )
        }
        composable(route = TeamsListNavigationInfo.TEAMS_LIST_DESTINATION_ROUTE) {
            val viewModel = hiltViewModel<TeamListViewModel>()
            TeamListScreen(
                modifier = modifier,
                teamListViewModel = viewModel,
                onClickTeam = {
                    navController.navigateSingleTopTo(TeamDetailsNavigationInfo.buildTeamDetailRoute(it))
                }
            )
        }
        composable(
            route = TeamDetailsNavigationInfo.TEAM_DETAIL_DESTINATION_ROUTE,
            arguments = listOf(navArgument(TeamDetailsNavigationInfo.TEAM_DETAIL_DESTINATION_ARG) {
                type = NavType.IntType
            })
        ) {
            val viewModel = hiltViewModel<TeamDetailViewModel>()
            TeamDetailScreen(
                modifier = modifier,
                teamDetailViewModel = viewModel,
                onPlayerClick = {
                    navController.navigateSingleTopTo(PlayerDetailsNavigationInfo.buildPlayerDetailRoute(it))
                }
            )
        }
    }
}

fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) {
        launchSingleTop = true
        restoreState = true
    }
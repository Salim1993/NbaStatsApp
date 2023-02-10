@file:OptIn(ExperimentalMaterial3Api::class)

package com.salim.nbastatsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.salim.nbastatsapp.navigation.NavigationHost
import com.salim.nbastatsapp.navigation.PlayerListNavigationInfo
import com.salim.nbastatsapp.navigation.TeamsListNavigationInfo
import com.salim.nbastatsapp.navigation.navigateSingleTopTo
import com.salim.nbastatsapp.ui.BottomNavigationBar
import com.salim.nbastatsapp.ui.BottomNavigationItems
import com.salim.nbastatsapp.ui.theme.NbaStatsAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NbaStatsApp()
        }
    }
}

@Composable
fun NbaStatsApp() {
    NbaStatsAppTheme {
        val navController = rememberNavController()
        // A surface container using the 'background' color from the theme
        Scaffold(
            bottomBar = { BottomNavigationBar(onClick = {
                when(it) {
                    BottomNavigationItems.PlayerList -> navController.navigateSingleTopTo(
                        PlayerListNavigationInfo.PLAYER_LIST_DESTINATION_ROUTE
                    )
                    BottomNavigationItems.TeamList ->  navController.navigateSingleTopTo(
                        TeamsListNavigationInfo.TEAMS_LIST_DESTINATION_ROUTE
                    )
                }
            }) }
        ) { contentPadding ->
            NavigationHost(modifier = Modifier, navController = navController)
        }
    }
}


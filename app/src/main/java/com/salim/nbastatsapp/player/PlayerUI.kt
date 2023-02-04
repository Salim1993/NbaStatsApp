package com.salim.nbastatsapp.player

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun PlayerListScreen(
    modifier: Modifier,
    playerListViewModel: PlayerListViewModel = viewModel()
) {
    val playerListState = playerListViewModel.playerList.collectAsState(initial = emptyList())
    PlayerList(modifier = modifier, list = playerListState.value)
}

@Composable
fun PlayerList(
    modifier: Modifier,
    list: List<PlayerAndTeam>
) {
    LazyColumn(modifier = modifier) {
        items(list) {
            PlayerCard(modifier = modifier, playerAndTeam = it)
        }
    }
}

@Composable
fun PlayerCard(
    modifier: Modifier,
    playerAndTeam: PlayerAndTeam
) {
    val player = playerAndTeam.player
    val team = playerAndTeam.team
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(
            modifier = modifier.padding(end = 8.dp),
            text = "${player.firstName} ${player.lastName}"
        )
        Text(
            modifier = modifier.weight(1.0f),
            text = team?.fullName ?: ""
        )
    }
}
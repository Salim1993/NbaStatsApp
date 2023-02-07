package com.salim.nbastatsapp.player.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.salim.nbastatsapp.R
import com.salim.nbastatsapp.player.Player
import com.salim.nbastatsapp.player.PlayerAndTeam
import com.salim.nbastatsapp.team.Team

@Composable
fun PlayerListScreen(
    modifier: Modifier,
    playerListViewModel: PlayerListViewModel = viewModel(),
    onClickPlayer: (Int) -> Unit = {}
) {
    val playerListState = playerListViewModel.playerList.collectAsState(initial = emptyList())
    PlayerList(modifier = modifier, list = playerListState.value, onClickPlayer = onClickPlayer)
}

@Composable
fun PlayerList(
    modifier: Modifier,
    list: List<PlayerAndTeam>,
    onClickPlayer: (Int) -> Unit = {}
) {
    LazyColumn(modifier = modifier) {
        items(list) {
            PlayerCard(modifier = modifier, playerAndTeam = it, onClickPlayer = onClickPlayer)
        }
    }
}

@Composable
fun PlayerCard(
    modifier: Modifier,
    playerAndTeam: PlayerAndTeam,
    onClickPlayer: (Int) -> Unit = {}
) {
    val player = playerAndTeam.player
    val team = playerAndTeam.team
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                onClickPlayer(player.id)
            }
    ) {
        Text(
            modifier = modifier.padding(end = 8.dp),
            text = "${player.firstName} ${player.lastName}"
        )
        Box(modifier = Modifier.weight(1.0f)) {
            Text(
                modifier = modifier.align(Alignment.CenterEnd),
                text = team?.fullName ?: stringResource(id = R.string.not_on_team)
            )
        }
    }
}

@Preview
@Composable
fun PreviewPlayerCard() {
    val player = Player(
        id = 0,
        firstName = "LMAO",
        heightFeet = null,
        heightInches = null,
        lastName = "ROFL",
        position = "",
        teamName = "",
        weightPounds = null
    )
    val team = Team(
        id = 0, abbreviation = "", city = "", conference = "", division = "", fullName = "L TEAM", name = ""
    )
    PlayerCard(modifier = Modifier, playerAndTeam = PlayerAndTeam(player, team))
}

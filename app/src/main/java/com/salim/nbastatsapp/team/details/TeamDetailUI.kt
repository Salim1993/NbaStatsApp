package com.salim.nbastatsapp.team.details

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.salim.nbastatsapp.R
import com.salim.nbastatsapp.player.Player
import com.salim.nbastatsapp.player.list.PlayerCard
import com.salim.nbastatsapp.team.Team
import com.salim.nbastatsapp.ui.theme.ComposeSizeConstants
import com.salim.nbastatsapp.utilities.LogoManager

@Composable
fun TeamDetailScreen(
    modifier: Modifier,
    teamDetailViewModel: TeamDetailViewModel = viewModel()
) {
    val teamState = teamDetailViewModel.teamFlow.collectAsState(
        initial = Team(
            id = 0,
            abbreviation = "",
            city = "",
            conference = "",
            division = "",
            fullName = "",
            name = ""
        )
    )

    val playerListState = teamDetailViewModel.playerListFlow.collectAsState(initial = emptyList())

    Column(modifier = modifier) {
        TeamDetailsPage(modifier = modifier, team = teamState.value)
        PlayerList(modifier = modifier, playerList = playerListState.value)
    }
}

@Composable
fun TeamDetailsPage(
    modifier: Modifier,
    team: Team
) {
    Column(modifier = modifier
        .fillMaxWidth()
        .verticalScroll(rememberScrollState())) {
        Box(
            modifier = modifier.fillMaxWidth(),
            contentAlignment = Alignment.TopCenter
        ) {
            AsyncImage(
                modifier = modifier
                    .fillMaxWidth(ComposeSizeConstants.DETAIL_PAGE_LOGO_SIZE)
                    .padding(8.dp),
                model = LogoManager.getProperLogo(team.fullName),
                contentDescription = stringResource(R.string.team_logo_content_description, team.fullName)
            )
        }

        CreateTeamDetailText(
            modifier = modifier,
            text = team.fullName,
            stringResourceId = R.string.team
        )

        CreateTeamDetailText(
            modifier = modifier,
            text = team.city,
            stringResourceId = R.string.city
        )

        CreateTeamDetailText(
            modifier = modifier,
            text = team.conference,
            stringResourceId = R.string.conference
        )
    }
}

@Composable
fun PlayerList(
    modifier: Modifier,
    playerList: List<Player>
) {
    Column(modifier = modifier) {
        Text(
            modifier = modifier.padding(8.dp),
            style = MaterialTheme.typography.headlineMedium,
            text = stringResource(id = R.string.player_list))
        
        LazyColumn(modifier = modifier) {
            items(playerList) {
                TeamPlayerDetail(modifier = modifier, player = it)
            }
        }
    }
}

@Composable
fun TeamPlayerDetail(
    modifier: Modifier,
    player: Player
) {
    Row(
        modifier = modifier
    ) {
        Text(modifier = modifier.padding(8.dp), text = player.getFullName())
        Box(modifier = Modifier.weight(1.0f)) {
            val text: String = if (player.position.isEmpty()) {
                stringResource(R.string.no_position)
            } else {
                stringResource(R.string.position, player.getFullPosition())
            }
            Text(
                modifier = modifier
                    .align(Alignment.CenterEnd)
                    .padding(8.dp),
                text = text
            )
        }
    }
}

@Composable
fun CreateTeamDetailText(
    modifier: Modifier,
    text: String,
    stringResourceId: Int
) {
    Text(
        modifier = modifier.padding(8.dp),
        text = stringResource(stringResourceId, text)
    )
}


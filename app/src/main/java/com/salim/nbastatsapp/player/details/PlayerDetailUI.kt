package com.salim.nbastatsapp.player.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.salim.nbastatsapp.R
import com.salim.nbastatsapp.player.Player
import com.salim.nbastatsapp.player.PlayerAndTeam
import com.salim.nbastatsapp.team.Team
import com.salim.nbastatsapp.ui.theme.ComposeSizeConstants
import com.salim.nbastatsapp.utilities.LogoManager

@Composable
fun PlayerDetailScreen(
    modifier: Modifier,
    playerDetailViewModel: PlayerDetailViewModel = viewModel(),
    teamOnClick: (Int) -> Unit = {}
) {
    val playerState by playerDetailViewModel.playerFlow.collectAsState()
    val teamState by playerDetailViewModel.teamFlow.collectAsState()
    
    PlayerDetailsPage(modifier = modifier, player = playerState, team = teamState, teamOnClick = teamOnClick)
}

@Composable
fun PlayerDetailsPage(
    modifier: Modifier,
    player: Player,
    team: Team,
    teamOnClick: (Int) -> Unit = {}
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
                model = LogoManager.getProperLogo(player.teamName),
                contentDescription = stringResource(R.string.team_logo_content_description, player.teamName)
            )
        }

        CreatePlayerDetailText(
            modifier = modifier,
            text = player.getFullName(),
            stringResourceId = R.string.player_name
        )

        CreatePlayerDetailText(
            modifier = modifier,
            text = player.getFullPosition(),
            stringResourceId = R.string.position
        )

        if(player.heightFeet == null || player.heightInches == null) {
            Text(modifier = modifier.padding(8.dp), text = stringResource(id = R.string.height_unavailable))
        } else {
            CreatePlayerDetailText(
                modifier = modifier,
                text = player.getFullHeight(),
                stringResourceId = R.string.height
            )
        }

        CreatePlayerDetailText(
            modifier = modifier,
            text = player.teamName,
            stringResourceId = R.string.team
        )

        if(player.weightPounds == null) {
            Text(modifier = modifier.padding(8.dp), text = stringResource(id = R.string.weight_unavailable))
        } else {
            CreatePlayerDetailText(
                modifier = modifier,
                text = player.weightPounds.toString(),
                stringResourceId = R.string.weight
            )
        }

        Button(
            modifier = modifier.padding(8.dp).fillMaxWidth(),
            onClick = {
                teamOnClick(team.id)
            }
        ) {
            Text(stringResource(id = R.string.go_to_team))
        }
    }
}

@Composable
fun CreatePlayerDetailText(
    modifier: Modifier,
    text: String,
    stringResourceId: Int
) {
    Text(
        modifier = modifier.padding(8.dp),
        text = stringResource(stringResourceId, text)
    )
}


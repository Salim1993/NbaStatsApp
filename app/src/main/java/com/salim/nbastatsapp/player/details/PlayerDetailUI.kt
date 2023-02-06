package com.salim.nbastatsapp.player.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.salim.nbastatsapp.R
import com.salim.nbastatsapp.player.Player

@Composable
fun PlayerDetailScreen(
    modifier: Modifier,
    playerDetailViewModel: PlayerDetailViewModel = viewModel()
) {
    val playerState = playerDetailViewModel.playerFlow.collectAsState(
        initial = Player(
            id = 0,
            firstName = "",
            heightFeet = null,
            heightInches = null,
            lastName = "",
            position = "",
            teamName = "",
            weightPounds = null
        )
    )
    
    PlayerDetailsPage(modifier = modifier, player = playerState.value)
}

@Composable
fun PlayerDetailsPage(
    modifier: Modifier,
    player: Player
) {
    Column(modifier = modifier.fillMaxWidth()) {
        CreatePlayerDetailText(
            modifier = modifier,
            text = player.getFullName(),
            stringResourceId = R.string.player_name
        )

        CreatePlayerDetailText(
            modifier = modifier,
            text = player.position,
            stringResourceId = R.string.position
        )
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


package com.salim.nbastatsapp.player.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.compose.AsyncImage
import com.salim.nbastatsapp.R
import com.salim.nbastatsapp.player.Player
import com.salim.nbastatsapp.utilities.LogoManager

@Composable
fun PlayerListScreen(
    modifier: Modifier,
    playerListViewModel: PlayerListViewModel = viewModel(),
    onClickPlayer: (Int) -> Unit = {}
) {
    val playerListState = playerListViewModel.playerListFlow.collectAsLazyPagingItems()
    PlayerList(modifier = modifier, list = playerListState, onClickPlayer = onClickPlayer)
}

@Composable
fun PlayerList(
    modifier: Modifier,
    list: LazyPagingItems<Player>,
    onClickPlayer: (Int) -> Unit = {}
) {
    LazyColumn(modifier = modifier) {
        items(
            items = list,
            key = { player -> player.id }
        ) {player ->
            player?.let {
                PlayerCard(modifier = modifier, player = it, onClickPlayer = onClickPlayer)
                Divider()
            }
        }
    }
}

@Composable
fun PlayerCard(
    modifier: Modifier,
    player: Player,
    onClickPlayer: (Int) -> Unit = {}
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                onClickPlayer(player.id)
            },
        verticalAlignment = CenterVertically
    ) {
        AsyncImage(
            modifier = modifier.heightIn(max = 48.dp),
            model = LogoManager.getProperLogo(player.teamName),
            contentDescription = stringResource(R.string.team_logo_content_description, player.teamName)
        )
        Text(
            modifier = modifier.padding(horizontal = 8.dp),
            text = "${player.firstName} ${player.lastName}"
        )
        Box(modifier = Modifier.weight(1.0f)) {
            Text(
                modifier = modifier.align(Alignment.CenterEnd),
                text = player.teamName
            )
        }
    }
}

@Preview
@Composable
fun PreviewPlayerCard() {
    val player = Player.returnEmptyPlayer().copy(
        firstName = "big",
        lastName = "dump",
        teamName = "Boston Celtics"
    )
    PlayerCard(modifier = Modifier, player = player)
}

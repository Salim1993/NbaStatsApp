package com.salim.nbastatsapp.team.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.salim.nbastatsapp.R
import com.salim.nbastatsapp.team.Team
import com.salim.nbastatsapp.utilities.LogoManager

@Composable
fun TeamListScreen(
    modifier: Modifier,
    teamListViewModel: TeamListViewModel = viewModel(),
    onClickTeam: (Int) -> Unit = {}
) {
    val teamListState = teamListViewModel.teamList.collectAsState(initial = emptyList())
    TeamList(modifier = modifier, list = teamListState.value, onClickTeam = onClickTeam)
}

@Composable
fun TeamList(
    modifier: Modifier,
    list: List<Team>,
    onClickTeam: (Int) -> Unit = {}
) {
    LazyColumn(modifier = modifier) {
        items(list) {
            TeamCard(modifier = modifier, team = it, onClickTeam = onClickTeam)
        }
    }
}

@Composable
fun TeamCard(
    modifier: Modifier,
    team: Team,
    onClickTeam: (Int) -> Unit = {}
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                onClickTeam(team.id)
            },
        verticalAlignment = CenterVertically
    ) {
        AsyncImage(
            modifier = modifier.heightIn(max = 48.dp),
            model = LogoManager.getProperLogo(team.fullName),
            contentDescription = stringResource(R.string.team_logo_content_description, team.fullName)
        )
        Text(
            modifier = modifier.padding(horizontal = 8.dp),
            text = team.fullName
        )
    }
}

@Preview
@Composable
fun PreviewPlayerCard() {
    val team = Team(
        id = 0, abbreviation = "", city = "", conference = "", division = "", fullName = "Golden State Warrior", name = ""
    )
    TeamCard(modifier = Modifier, team = team)
}

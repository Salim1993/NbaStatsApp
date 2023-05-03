package com.salim.nbastatsapp.team.list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
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
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Adaptive(minSize = 128.dp)
    ) {
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
    val backGroundColor = LogoManager.getLogoColor(team.fullName)
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(color = backGroundColor, shape = RoundedCornerShape(20.dp))
            .clickable {
                onClickTeam(team.id)
            }
            .clip(shape = RoundedCornerShape(5.dp))
            .defaultMinSize(minHeight = 165.dp),
        horizontalAlignment = CenterHorizontally
    ) {
        AsyncImage(
            modifier = modifier.heightIn(max = 96.dp),
            model = LogoManager.getProperLogo(team.fullName),
            contentDescription = stringResource(R.string.team_logo_content_description, team.fullName)
        )
        val textColor = LogoManager.getTextColor(team.fullName)
        Text(
            modifier = modifier
                .padding(horizontal = 4.dp),
            color = textColor,
            text = team.fullName,
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
fun PreviewTeamCard() {
    val team = Team(
        id = 0,
        abbreviation = "",
        city = "",
        conference = "",
        division = "",
        fullName = "Golden State Warriors",
        name = ""
    )
    TeamCard(modifier = Modifier, team = team)
}

@Preview
@Composable
fun PreviewTeamList() {
    val team = Team(
        id = 0,
        abbreviation = "",
        city = "",
        conference = "",
        division = "",
        fullName = "Golden State Warriors",
        name = ""
    )
    val team2 = team.copy(fullName = "Philadelphia 76ers")
    val list = listOf(team, team2)
    TeamList(modifier = Modifier, list = list)
}

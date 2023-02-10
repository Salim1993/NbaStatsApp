package com.salim.nbastatsapp.team.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
    
    PlayerDetailsPage(modifier = modifier, team = teamState.value)
}

@Composable
fun PlayerDetailsPage(
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

        CreatePlayerDetailText(
            modifier = modifier,
            text = team.fullName,
            stringResourceId = R.string.team
        )

        CreatePlayerDetailText(
            modifier = modifier,
            text = team.city,
            stringResourceId = R.string.city
        )

        CreatePlayerDetailText(
            modifier = modifier,
            text = team.conference,
            stringResourceId = R.string.conference
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


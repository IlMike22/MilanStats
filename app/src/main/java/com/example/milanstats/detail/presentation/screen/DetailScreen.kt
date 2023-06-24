package com.example.milanstats.detail.presentation.screen

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.InputChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.example.milanstats.R
import com.example.milanstats.detail.presentation.DetailState
import com.example.milanstats.detail.presentation.DetailUiEvent
import com.example.milanstats.detail.presentation.screen.components.TableInformation
import com.example.milanstats.overview.domain.model.Penalty

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    state: DetailState,
    onEvent: (DetailUiEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.size(32.dp))
        } else if (state.errorMessage.isNullOrBlank().not()) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(16.dp)
            ) {
                Text(
                    text = stringResource(R.string.detail_screen_error_title),
                    style = MaterialTheme.typography.headlineLarge
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = state.errorMessage ?: stringResource(R.string.detail_screen_error_text),
                    style = MaterialTheme.typography.displaySmall,
                    color = Color.Red
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
                .horizontalScroll(rememberScrollState()),

            ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(text = state.teamDetails.name, style = MaterialTheme.typography.headlineLarge)
                Spacer(modifier = Modifier.width(8.dp))
                AsyncImage(
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape),
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(state.teamDetails.logo)
                        .decoderFactory(SvgDecoder.Factory())
                        .build(),
                    contentDescription = null
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(
                    id = R.string.detail_screen_text_founded_date,
                    state.teamDetails.foundedYear
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            if (state.teamDetails.penaltyData != Penalty.EMPTY) {
                Text(
                    text = stringResource(R.string.detail_screen_text_penalty_bilance),
                    style = MaterialTheme.typography.headlineMedium
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(
                            id = R.string.detail_screen_text_penalty_total,
                            state.teamDetails.penaltyData.total
                        )
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = stringResource(
                            id = R.string.detail_screen_text_penalty_scored,
                            state.teamDetails.penaltyData.totalScored
                        )
                    )
                    Spacer(Modifier.width(16.dp))
                    Text(
                        text = stringResource(
                            id = R.string.detail_screen_text_penalty_missed,
                            state.teamDetails.penaltyData.totalMissed
                        )
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = stringResource(R.string.detail_screen_text_team_bilance))
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    state.teamDetails.teamForms.forEach {
                        InputChip(
                            selected = false,
                            onClick = {},
                            label = {
                                Text(text = it.name)
                            })
                    }
                }
                state.teamDetails.teamForms.forEach {

                }
            }
            if (state.teamDetails.tableInformation.standings.isNotEmpty()) {
                Spacer(modifier = Modifier.height(24.dp))
                TableInformation(tableInformation = state.teamDetails.tableInformation)
            }
        }
    }
}
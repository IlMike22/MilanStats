package com.example.milanstats.overview.presentation.screen

import android.annotation.SuppressLint
import android.content.Context
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.InputChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.example.milanstats.R
import com.example.milanstats.navigation.Route
import com.example.milanstats.overview.domain.model.Penalty
import com.example.milanstats.overview.presentation.OverviewEvent
import com.example.milanstats.overview.presentation.OverviewState

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OverviewScreen(
    modifier: Modifier = Modifier,
    state: OverviewState,
    onEvent: (OverviewEvent) -> Unit,
    navController: NavController
) {
    val context: Context = LocalContext.current
    val focusRequester = remember { FocusRequester() }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomAppBar(
                actions = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = "Show favorites"
                        )
                    }
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = "Show settings"
                        )
                    }
                }
            )
        }
    ) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            if (state.error != null) {
                Text(
                    text = state.error,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Red
                )
            } else if (state.isLoading) {
                CircularProgressIndicator(modifier = Modifier.size(32.dp))
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(8.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    Text(
                        text = state.greetingsText,
                        style = MaterialTheme.typography.headlineMedium
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .focusRequester(focusRequester),
                        textStyle = TextStyle(color = Black),
                        placeholder = {
                            if (state.searchText.isBlank()) Text(text = "Search for a famous team")
                        },
                        trailingIcon = {},
                        value = state.searchText,
                        onValueChange = { newText ->
                            onEvent(OverviewEvent.OnSearchTextChanged(newText))
                        }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedButton(onClick = {
                        onEvent(OverviewEvent.OnSearchClicked)
                        navController.navigate(Route.DETAIL + "?teamName=${state.searchText}")
                    }) {
                        Text(text = "Start search")
                    }
                    Spacer(modifier = Modifier.height(24.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    ) {
                        AsyncImage(
                            modifier = Modifier
                                .size(32.dp)
                                .clip(CircleShape),
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(state.teamLogo)
                                .decoderFactory(SvgDecoder.Factory())
                                .build(),
                            contentDescription = null
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(
                            text = state.teamName,
                            style = MaterialTheme.typography.headlineMedium
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(
                            text = "${state.teamCountry.name} (${state.teamCountry.id})",
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(top = 12.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = stringResource(R.string.overview_screeen_current_statistics_title),
                        style = MaterialTheme.typography.headlineSmall
                    )

                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .horizontalScroll(rememberScrollState())
                    ) {
                        state.teamStatistic.teamForms.forEach {
                            InputChip(
                                selected = false,
                                onClick = {},
                                label = {
                                    Text(text = it.name)
                                }
                            )
                            Spacer(modifier = Modifier.width(2.dp))
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    if (state.teamStatistic.penalty != Penalty.EMPTY) {
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
                                    state.teamStatistic.penalty.total
                                )
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            Text(
                                text = stringResource(
                                    id = R.string.detail_screen_text_penalty_scored,
                                    state.teamStatistic.penalty.totalScored
                                )
                            )
                            Spacer(Modifier.width(16.dp))
                            Text(
                                text = stringResource(
                                    id = R.string.detail_screen_text_penalty_missed,
                                    state.teamStatistic.penalty.totalMissed
                                )
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = stringResource(R.string.detail_screen_text_team_bilance),
                        style = MaterialTheme.typography.headlineMedium
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = stringResource(
                                R.string.overview_screen_team_balance_biggest_win_home,
                                state.teamStatistic.biggest.homeWin
                            ),
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = stringResource(
                                R.string.overview_screen_team_balance_biggest_win_away,
                                state.teamStatistic.biggest.awayWin
                            ),
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = stringResource(
                                R.string.overview_screen_team_balance_biggest_defeat_home,
                                state.teamStatistic.biggest.homeDefeat
                            ),
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = stringResource(
                                R.string.overview_screen_team_balance_biggest_defeat_away,
                                state.teamStatistic.biggest.awayDefeat
                            ),
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Current table",
                        style = MaterialTheme.typography.headlineMedium
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "TODO: Start next time with that table view. And look why scrolling is not possible right now")
                }
            }
        }
    }
}

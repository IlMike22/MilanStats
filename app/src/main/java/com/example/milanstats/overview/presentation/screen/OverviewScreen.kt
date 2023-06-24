package com.example.milanstats.overview.presentation

import android.content.Context
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.example.milanstats.R
import com.example.milanstats.navigation.Route

@Composable
fun OverviewScreen(
    modifier: Modifier = Modifier,
    state: OverviewState,
    onEvent: (OverviewEvent) -> Unit,
    navController: NavController
) {
    val context: Context = LocalContext.current

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
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
            ) {
                Button(onClick = {
                    //TODO MIC not sure if this is the right way, better use VM to handle navigation?
                    navController.navigate(Route.DETAIL + "?teamName={${state.teams.first().name})")
                }) {
                    Text(
                        text = stringResource(R.string.overview_screen_show_details_button_text)
                    )
                }

                Button(onClick = {
                    onEvent(OverviewEvent.CallApiAgain)
                }) {
                    Text(
                        text = stringResource(R.string.overview_screen_call_api_again_button_text)
                    )
                }
                state.countries.forEach { country ->
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
                                .data(country.flag)
                                .decoderFactory(SvgDecoder.Factory())
                                .build(),
                            contentDescription = null
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(
                            text = "${country.name} (${country.id})",
                            style = MaterialTheme.typography.headlineSmall
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    if (state.leagues.isNotEmpty()) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {
                            state.leagues.forEach { league ->
                                Text(text = league.name)
                                Spacer(modifier = Modifier.width(16.dp))
                                AsyncImage(
                                    modifier = Modifier
                                        .size(32.dp)
                                        .clip(CircleShape),
                                    model = ImageRequest.Builder(context)
                                        .data(league.logo)
                                        .decoderFactory(SvgDecoder.Factory())
                                        .build(),
                                    contentDescription = null
                                )
                            }
                        }
                    }
                    if (state.teams.isNotEmpty()) {
                        Spacer(modifier = Modifier.height(16.dp))
                        Column(
                            modifier = Modifier
                                .fillMaxHeight()
                                .padding(16.dp)
                        ) {
                            state.teams.forEach { team ->
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(8.dp)
                                ) {
                                    Text(text = team.name)
                                    Spacer(modifier = Modifier.width(16.dp))
                                    Text(text = team.country)
                                    Spacer(modifier = Modifier.width(16.dp))
                                    AsyncImage(
                                        modifier = Modifier
                                            .size(32.dp)
                                            .clip(CircleShape),
                                        model = ImageRequest.Builder(context)
                                            .data(team.logo)
                                            .decoderFactory(SvgDecoder.Factory())
                                            .build(),
                                        contentDescription = null
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
package com.example.milanstats.injuries.presentation

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.example.milanstats.injuries.domain.model.Injury

@Composable
fun InjuryScreen(
    state: InjuriesState,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(16.dp, top = 64.dp),
    ) {
        if (state.isLoading) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator(Modifier.size(16.dp))
            }
        } else if (state.errorMessage != null) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = state.errorMessage)
            }
        } else {
            state.injuries.distinctBy { it.playerId }.forEach {
                Injury(injury = it)
            }
        }
    }
}

@Composable
fun Injury(
    injury: Injury,
    modifier: Modifier = Modifier
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(text = injury.name, style = MaterialTheme.typography.headlineSmall)
            Spacer(modifier = Modifier.width(16.dp))
            AsyncImage(
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(injury.photo)
                    .decoderFactory(SvgDecoder.Factory())
                    .build(),
                contentDescription = null
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = "Reason: ${injury.reason}")
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = "Fixture date: ${injury.fixtureDate}")
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = "Type: ${injury.type}")
        Spacer(modifier = Modifier.height(32.dp))
    }
}
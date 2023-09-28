package com.example.milanstats.injury.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.example.milanstats.injury.domain.model.Injury

@Composable
fun InjuryScreen(
    state: InjuryState,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(16.dp, top = 64.dp)
            .verticalScroll(rememberScrollState())
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
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = state.errorMessage,
                    color = Color.Red,
                    modifier = Modifier.padding(16.dp)
                )
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
    Box(
        modifier = Modifier
            .size(width = 1000.dp, height = 130.dp)
            .padding(16.dp)
    ) {
        Text(
            text = injury.name,
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.align(Alignment.TopStart)
        )
        Spacer(modifier = Modifier.width(16.dp))
        AsyncImage(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .size(32.dp)
                .clip(CircleShape),
            model = ImageRequest.Builder(LocalContext.current)
                .data(injury.photo)
                .decoderFactory(SvgDecoder.Factory())
                .build(),
            contentDescription = null
        )
        Text(
            text = "Reason: ${injury.reason}",
            modifier = Modifier.align(Alignment.CenterStart)
        )
    }
}
package com.example.milanstats.detail.presentation.screen

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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.example.milanstats.detail.presentation.DetailState
import com.example.milanstats.detail.presentation.DetailUiEvent
import com.example.milanstats.overview.domain.model.Penalty

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
        } else if (state.isError.isNullOrBlank().not()) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(16.dp)
            ) {
                Text(text = "An error occured!", style = MaterialTheme.typography.headlineLarge)
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = state.isError ?: "Unknown error :(",
                    style = MaterialTheme.typography.displaySmall,
                    color = Color.Red
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(16.dp)
        ) {
            Text(text = "Hello detail screen!", style = MaterialTheme.typography.labelSmall)
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(text = state.isSuccess.name, style = MaterialTheme.typography.headlineLarge)
                Spacer(modifier = Modifier.width(8.dp))
                AsyncImage(
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape),
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(state.isSuccess.logo) // TODO MIC get rid of isSuccess.XY 
                        .decoderFactory(SvgDecoder.Factory())
                        .build(),
                    contentDescription = null
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Gründungsjahr: ${state.isSuccess.foundedYear}")
            Spacer(modifier = Modifier.height(16.dp))
            if (state.isSuccess.penaltyData != Penalty.EMPTY) {
                Text(text = "Elfmeterbilanz", style = MaterialTheme.typography.headlineMedium)
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(text = "Total: ${state.isSuccess.penaltyData.total}")
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(text = "Getroffen: ${state.isSuccess.penaltyData.totalScored}")
                    Spacer(Modifier.width(16.dp))
                    Text(text = "Verschossen: ${state.isSuccess.penaltyData.totalMissed}")
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Teambilanz: ${state.isSuccess.teamForm}")
            }
        }
    }
}
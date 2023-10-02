package com.example.milanstats.goalscorer.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.example.milanstats.goalscorer.domain.model.GoalScorer
import com.example.milanstats.goalscorer.presentation.state.GoalScorerState

@Composable
fun GoalScorerScreen(
    state: GoalScorerState,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        if (state.isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else if (state.errorMessage != null) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "An error occured. Details: ${state.errorMessage}")
            }
        } else {
            Column {
                Spacer(modifier = Modifier.height(64.dp))
                state.goalScorers.forEach { goalScorer ->
                    GoalScorer(goalScorer)
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}

@Composable
fun GoalScorer(goalScorer: GoalScorer, modifier: Modifier = Modifier) {
    Card(
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(),
    ) {
        Box(
            modifier = modifier
                .height(120.dp)
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(
                text = goalScorer.name,
                modifier = Modifier.align(Alignment.TopStart),
                style = MaterialTheme.typography.headlineSmall
            )
            Column(modifier = Modifier.align(Alignment.TopEnd)) {
                AsyncImage(
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape),
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(goalScorer.photo)
                        .decoderFactory(SvgDecoder.Factory())
                        .build(),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextButton(
                    onClick = { /*TODO*/ }) {
                    Text(text = "Show details")
                }
            }
            Spacer(modifier = Modifier.height(4.dp))
            Column(
                modifier = Modifier.align(Alignment.CenterStart)
            ) {
                Row() {
                    Text(
                        text = "${goalScorer.statistics.goals} goals,",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    val assists = goalScorer.statistics.assists

                    Text(
                        text = if (assists == 0) "no assists" else "$assists assists",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
            Row(modifier = Modifier.align(Alignment.BottomStart)) {
                Column() {
                    Text(
                        text = goalScorer.statistics.teamName,
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = goalScorer.statistics.position,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}
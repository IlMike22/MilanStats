package com.example.milanstats.goalscorer.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
                }
            }
        }
    }
}

@Composable
fun GoalScorer(goalScorer: GoalScorer, modifier: Modifier = Modifier) {
    Text(
        text = goalScorer.name,
        style = MaterialTheme.typography.headlineSmall
    )
    Spacer(modifier = Modifier.height(16.dp))
    Box(
        modifier = modifier
            .height(80.dp)
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(
            text = "Goals scored: ${goalScorer.statistics.goals}",
            modifier = Modifier.align(Alignment.TopStart),
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = "Assists made: ${goalScorer.statistics.assists}",
            modifier = Modifier.align(Alignment.CenterStart),
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = goalScorer.statistics.teamName,
            modifier = Modifier.align(Alignment.TopEnd),
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = goalScorer.statistics.position,
            modifier = Modifier.align(Alignment.CenterEnd),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}
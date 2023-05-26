package com.example.milanstats.overview.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun OverviewScreen(
    modifier: Modifier = Modifier,
    state: OverviewState
) {
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
            Text(
                text = state.countries,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}
package com.example.milanstats.table.presentation

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.milanstats.table.presentation.components.TableInformation

@Composable
fun TableScreen(
    state: TableState,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
            .horizontalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(64.dp))
        Text(text = "Current table", style = MaterialTheme.typography.headlineLarge)
        if (state.isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else if (state.errorMessage != null) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(
                    text = "An error occured while loeading the table information.",
                    style = MaterialTheme.typography.bodyMedium, color = Color.Red
                )
            }
        } else {
            if (state.tableInformation == null) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(
                        text = "TableInformation is null. That should no be the case.",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                return
            }

            if (state.tableInformation.standings.isNotEmpty()) {
                TableInformation(tableInformation = state.tableInformation)
            }
        }
    }
}
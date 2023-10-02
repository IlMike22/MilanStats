package com.example.milanstats.table.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
    if (state.isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }
//    else if (state.tableInformation?.errorMessage != null) {
//        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
//            Text(
//                modifier = Modifier.align(Alignment.Center).padding(16.dp),
//                text = state.tableInformation.errorMessage,
//                style = MaterialTheme.typography.bodyMedium, color = Color.Red
//            )
//        }
//    }
else {
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
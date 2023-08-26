package com.example.milanstats.table.presentation.components

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.example.milanstats.table.domain.model.TableInformation

@Composable
fun TableInformation(
    modifier: Modifier = Modifier,
    tableInformation: TableInformation,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxHeight()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(text = "Rank")
                Spacer(modifier = Modifier.width(16.dp))
                Text(text = "Team")
                Spacer(modifier = Modifier.width(16.dp))
                Text(text = "Points")
                Spacer(modifier = Modifier.width(16.dp))
                Text(text = "Wins")
                Spacer(modifier = Modifier.width(16.dp))
                Text(text = "Draws")
                Spacer(modifier = Modifier.width(16.dp))
                Text(text = "Lose")
                Spacer(modifier = Modifier.width(16.dp))
            }

            Spacer(modifier = Modifier.height(16.dp))

            tableInformation.standings.forEach { standing ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Text(text = standing.rank.toString())
                    Spacer(modifier = Modifier.width(16.dp))
                    AsyncImage(
                        modifier = Modifier
                            .size(24.dp)
                            .clip(CircleShape),
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(standing.team.logo)
                            .decoderFactory(SvgDecoder.Factory())
                            .build(),
                        contentDescription = null
                    )
                    Spacer(modifier.width(8.dp))
                    Text(text = standing.team.name)
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(text = standing.points.toString())
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(text = standing.standing.played.toString())
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(text = standing.standing.win.toString())
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(text = standing.standing.draw.toString())
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(text = standing.standing.lose.toString())
                    Spacer(modifier = Modifier.width(16.dp))
                }
            }
        }
    }
}
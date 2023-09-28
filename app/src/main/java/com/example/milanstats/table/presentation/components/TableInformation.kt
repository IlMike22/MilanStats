package com.example.milanstats.table.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
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
    val weightRank = .1f
    val weightTeam = .4f
    val weightPointData = .1f

    LazyColumn(
        Modifier
            .fillMaxSize()
            .padding(top = 64.dp)
            .padding(16.dp)
    ) {
        item {
            Row {
                TableCell(text = "R", weight = weightRank, isBold = true)
                TableCell(text = "L", weight = weightRank, isBold = true)
                TableCell(text = "Team", weight = weightTeam, isBold = true)
                TableCell(text = "P", weight = weightPointData, isBold = true)
                TableCell(text = "W", weight = weightPointData, isBold = true)
                TableCell(text = "D", weight = weightPointData, isBold = true)
                TableCell(text = "L", weight = weightPointData, isBold = true)
            }
        }

        items(tableInformation.standings) {
            Row(Modifier.fillMaxWidth()) {
                TableCell(text = it.rank.toString(), weight = weightRank)
                TableCell(logo = it.team.logo, weight = weightPointData)
                TableCell(text = it.team.name, weight = weightTeam)
                TableCell(text = it.points.toString(), weight = weightPointData)
                TableCell(text = it.standing.win.toString(), weight = weightPointData)
                TableCell(text = it.standing.draw.toString(), weight = weightPointData)
                TableCell(text = it.standing.lose.toString(), weight = weightPointData)
            }
        }
    }
}

@Composable
fun RowScope.TableCell(
    text: String? = null,
    weight: Float,
    isBold: Boolean = false,
    logo: String? = null
) {
    if (logo != null) {
        AsyncImage(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .border(1.dp, Color.Black)
                .weight(weight)
                .padding(8.dp),
            model = ImageRequest.Builder(LocalContext.current)
                .data(logo)
                .decoderFactory(SvgDecoder.Factory())
                .build(),
            contentDescription = null
        )
    }
    if (text != null) {
        Text(
            text = text,
            Modifier
                .border(1.dp, Color.Black)
                .weight(weight)
                .padding(8.dp),
            fontWeight = if (isBold) FontWeight.Bold else FontWeight.Normal
        )
    }
}
package com.fonagyma.rikiki.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fonagyma.rikiki.logic.Player
import java.lang.NumberFormatException

@Composable
fun ChooseCallWinner(
    callNumber: Int,
    playersList: List<Player>,
    onPlayerChosen: (Int) -> Unit
) {

        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp)
        ){
            item {
                Text(
                    text = "Call No. $callNumber",
                    fontSize = MaterialTheme.typography.headlineLarge.fontSize,
                    fontWeight = MaterialTheme.typography.headlineLarge.fontWeight,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.fillMaxWidth()
                        .padding(16.dp),
                    textAlign = TextAlign.Center
                )
            }
            items(playersList){
                ElevatedButton(
                    onClick = { onPlayerChosen(playersList.indexOf(it)) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(6.dp)
                ){
                    Text(
                        text = it.name,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(5.dp)
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
}
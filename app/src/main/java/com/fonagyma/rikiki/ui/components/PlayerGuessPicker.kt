package com.fonagyma.rikiki.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.textInputServiceFactory
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PlayerGuessPicker(
    currentGuess: Int,
    maxGuess: Int,
    playerID: Int,
    onNewValue: (Int) -> Unit,
    onChosen: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
    ) {
        Surface(
            modifier = Modifier.padding(bottom = 36.dp),
            color = MaterialTheme.colorScheme.inverseSurface,
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                text = "playerID : $playerID",
                fontSize = 42.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color= MaterialTheme.colorScheme.inversePrimary,
                modifier = Modifier
                    .padding(5.dp, 5.dp, 5.dp, 5.dp)
                    .fillMaxWidth()
            )
        }

        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            ElevatedButton(
                onClick = { onNewValue(currentGuess-1) },
                enabled = (currentGuess>0)
            ){
                Text(
                    text = "-",
                    fontSize = 60.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(5.dp, 5.dp, 5.dp, 5.dp)
                )
            }
            ElevatedButton(
                onClick = {}
            ){
                Text(
                    text = "$currentGuess",
                    fontSize = 60.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(5.dp, 5.dp, 5.dp, 5.dp)
                )
            }
            ElevatedButton(
                onClick = { onNewValue(currentGuess+1) },
                enabled = (currentGuess<maxGuess)
            ){
                Text(
                    text = "+",
                    fontSize = 60.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(5.dp, 5.dp, 5.dp, 5.dp)
                )
            }
        }

        Slider(
            value = currentGuess / maxGuess.toFloat(),
            steps = maxGuess - 1,
            onValueChange = { it -> onNewValue((it * maxGuess).toInt()) },
            modifier = Modifier.padding(15.dp)
        )

        ElevatedButton(
            onClick = onChosen,
            enabled = (currentGuess in 0..maxGuess),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ){
            Text(
                text = "DONE",
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(5.dp, 5.dp, 5.dp, 5.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
    }
}
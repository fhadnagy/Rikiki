package com.fonagyma.rikiki.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fonagyma.rikiki.logic.Player

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputScreen(
    maxRounds:Int,
    addPlayer: (Player) -> Unit,
    removePlayer: (Player) -> Unit,
    playerList: List<Player>,
    modifier: Modifier = Modifier,
    onMaxRoundChange: (Int) -> Unit
) {

    Column(modifier = modifier.fillMaxSize()) {
        Row(
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "MaxRounds: ",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(5.dp),
                textAlign = TextAlign.Left
            )
            OutlinedTextField(
                value = maxRounds.toString(),
                onValueChange = {
                    if(it.isNotEmpty()){
                        onMaxRoundChange(it.toInt())
                    }else{
                        onMaxRoundChange(1)
                    }
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
        }
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ){
            items(playerList){
                PlayerRow(it,{removePlayer(it)},
                    Modifier
                        .fillMaxWidth()
                        .padding(6.dp))
            }
        }
    }
}

@Composable
fun PlayerRow(player: Player, onClose: ()->Unit,modifier: Modifier) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = player.name,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(5.dp),
            textAlign = TextAlign.Left
        )
        IconButton(onClick = onClose,Modifier.padding(5.dp)) {
            Icon(Icons.Default.Delete, contentDescription = null)
        }
    }
}
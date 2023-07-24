package com.fonagyma.rikiki.ui.components

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import com.fonagyma.rikiki.logic.Player

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputScreen(
    maxRounds: Int,
    addPlayer: (Player) -> Unit,
    removePlayer: (Player) -> Unit,
    playerList: List<Player>,
    modifier: Modifier = Modifier,
    onMaxRoundChange: (Int) -> Unit,
    increaseID: () -> Unit,
    lastPlayerID: Int,
    onDone: () -> Unit
) {
    var inputField by remember {
        mutableStateOf("new player")
    }
    var scrollingState = rememberScrollState(0)
    Column(
        verticalArrangement=Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
        .verticalScroll(scrollingState)
        .fillMaxSize()
        .padding(10.dp)) {
        BorderSurround{
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(50.dp, 200.dp)
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = "Highest card count: ",
                    fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                    fontWeight = MaterialTheme.typography.bodyLarge.fontWeight,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .padding(5.dp),
                    textAlign = TextAlign.Left
                )
                OutlinedTextField(
                    value = maxRounds.toString(),
                    onValueChange = {
                        //val str = it.trim()
                        Log.d("maxv",it)
                        if (it.isNotBlank() && it.isDigitsOnly()) {
                            onMaxRoundChange(it.toInt())
                        }
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal, ),
                    maxLines = 1,
                    singleLine = true
                )
            }
        }
        BorderSurround {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(50.dp, 300.dp)
                    .padding(10.dp)
            ){
                items(playerList){
                    PlayerRow(it,{removePlayer(it)},
                        Modifier
                            .fillMaxWidth()
                            .padding(6.dp))
                }
            }
        }
        BorderSurround {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(50.dp, 200.dp)
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                OutlinedTextField(value = inputField, onValueChange = { inputField=it}, singleLine = true)
                IconButton(onClick = {addPlayer(Player(lastPlayerID+1,inputField));increaseID()},Modifier.padding(5.dp)) {
                    Icon(Icons.Default.Add, contentDescription = null)
                }
            }
        }
        /**
         * leaving space for additional game modifiers
         * either as checkable or multi choice button
         */
        ElevatedButton(onClick = onDone, border = BorderStroke(3.dp,MaterialTheme.colorScheme.inverseSurface), modifier = Modifier.padding(vertical = 24.dp), enabled = (playerList.size>1)) {
            Text(
                text = "GO",
                fontWeight = MaterialTheme.typography.headlineMedium.fontWeight,
                fontSize = MaterialTheme.typography.headlineMedium.fontSize,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(10.dp)
            )
        }
    }
}

@Composable
fun PlayerRow(player: Player, onClose: ()->Unit,modifier: Modifier) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "${player.name} <${player.ID}>",
            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
            fontWeight = MaterialTheme.typography.bodyMedium.fontWeight,
            color = MaterialTheme.colorScheme.secondary,
            modifier = Modifier
                .padding(5.dp),
            textAlign = TextAlign.Left
        )
        IconButton(onClick = onClose,Modifier.padding(5.dp)) {
            Icon(Icons.Default.Delete, contentDescription = null)
        }
    }
}

@Composable
fun BorderSurround(content:  @Composable () -> Unit) {
    Surface(
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(3.dp,MaterialTheme.colorScheme.inversePrimary),
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        content()
    }
}
package com.fonagyma.rikiki.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.fonagyma.rikiki.logic.Player
import kotlin.math.abs

@Composable
fun GameStateDisplay(playerList: List<Player>) {
    /*Text(
        text = "display",
        fontSize = MaterialTheme.typography.titleLarge.fontSize,
        fontWeight = MaterialTheme.typography.titleLarge.fontWeight,
        color = MaterialTheme.colorScheme.primary,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp),
    )*/
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(50.dp, 600.dp)
    ){
        items(playerList){
            PlayerDisplayRow(modifier = Modifier.fillMaxWidth(), player = it)
        }
    }
}

@Composable
fun PlayerDisplayRow(modifier: Modifier,player: Player) {
    val outList = mutableListOf<String>()
    val sum = populateListOfPlayer(outList,player)

    LazyRow(modifier = modifier
        .padding(5.dp)) {
        item { Text(
            text = "${player.name} <${player.ID}>",
            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
            fontWeight = MaterialTheme.typography.bodyMedium.fontWeight,
            color = MaterialTheme.colorScheme.secondary,
            textAlign = TextAlign.Left,
            modifier = Modifier
                .padding(5.dp)
                .width(110.dp)
        ) }
        items(outList){
            Text(
                text = "$it",
                fontSize = MaterialTheme.typography.bodySmall.fontSize,
                fontWeight = MaterialTheme.typography.bodySmall.fontWeight,
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Left,
                modifier = Modifier
                    .padding(5.dp)
            )
        }
        item {
            Text(
                text = "$sum",
                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                fontWeight = MaterialTheme.typography.bodyMedium.fontWeight,
                color = MaterialTheme.colorScheme.secondary,
                textAlign = TextAlign.Left,
                modifier = Modifier.padding(5.dp)
            )
        }
    }
}

fun populateListOfPlayer(values: MutableList<String>,player: Player) : Int{
    var sum = 0
    var det : Int
    for (i in 0 until player.rounds){
        det =0
        if(player.guesses[i]>-1 && player.handswon[i]>-1){
            det = if(player.guesses[i]==player.handswon[i]){
                player.guesses[i]+10
            }else{
                -1*abs(player.guesses[i]-player.handswon[i])
            }
            sum+=det
        }
        values.add("${player.guesses[i]}/${player.handswon[i]}($det)")

    }
    return 0
}
package com.fonagyma.rikiki.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.fonagyma.rikiki.logic.Player

@Composable
fun InputScreen(
    maxRounds:Int,
    addPlayer: (Player) -> Unit,
    removePlayer: (Player) -> Unit,
    playerList: List<Player>,
    modifier: Modifier = Modifier
) {

}
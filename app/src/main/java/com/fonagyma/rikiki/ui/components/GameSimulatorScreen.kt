package com.fonagyma.rikiki.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.fonagyma.rikiki.logic.Player

@Composable
fun GameSimulatorScreen (
    players: List<Player>,
    maxRounds: Int,
    onGameFinished: () -> Unit,
    initRound: Int = 1,
    initPlayerID: Int = 0){
    var currentRound by remember { mutableStateOf(1) }
    var currentCall by remember { mutableStateOf(1) }
    var currentPlayerID by remember { mutableStateOf(0)}
    var guessPhase by remember { mutableStateOf(true)}
    /**NEW ROUND
     *    player 0->n Guesses
     *    CHoose winner for call * currentRound
     *
     */
    Column(modifier = Modifier.fillMaxWidth()) {
        if (guessPhase){
            //TODO: guess
        }else{
            //TODO: pick winner for calls
        }
    }
}
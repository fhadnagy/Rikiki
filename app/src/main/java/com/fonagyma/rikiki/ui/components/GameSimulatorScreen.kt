package com.fonagyma.rikiki.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.fonagyma.rikiki.GuessPickerScreen
import com.fonagyma.rikiki.logic.Player

@Composable
fun GameSimulatorScreen (
    players: List<Player>,
    maxRounds: Int,
    onGameFinished: () -> Unit,
    playerGuess: (Int,Int,Int) -> Unit,
    playerCall: (Int,Int) -> Unit,
    initRound: Int = 1,
    initPlayerID: Int = 0,
    initPhase : Boolean = true,
    initCall : Int = 1){
    var currentRound by remember { mutableStateOf(initRound) }
    var roundIndex by remember { mutableStateOf(0) }
    var currentGuess by remember { mutableStateOf(0) }
    var currentCall by remember { mutableStateOf(initCall) }
    var currentPlayerID by remember { mutableStateOf(initPlayerID)}
    var guessPhase by remember { mutableStateOf(initPhase)}
    var stepUp by remember { mutableStateOf(true)}
    /**NEW ROUND
     *    player 0->n Guesses
     *    CHoose winner for call * currentRound
     *
     */
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (guessPhase){
                PlayerGuessPicker(
                    maxGuess = currentRound,
                    player = players[currentPlayerID],
                    onNewValue = { newGuess -> currentGuess = newGuess },
                    onChosen = {
                                //add the guess to the current players guess list
                                playerGuess(currentPlayerID,currentGuess,roundIndex)
                                //update the flow control variables
                                if (currentPlayerID==players.size-1) {
                                    guessPhase=false
                                    currentPlayerID=0
                                }else{
                                    currentPlayerID++
                                }
                               },
                    currentGuess = currentGuess
                )

        }else{
            ChooseCallWinner(
                callNumber = currentCall,
                playersList = players,
                onPlayerChosen = {
                    //value update
                        id -> playerCall(id, roundIndex)
                    //flow control update
                    if(currentCall==currentRound){
                        guessPhase=true
                        currentCall=1

                        //update round number
                        if (currentRound == maxRounds){
                            stepUp = false
                        }
                        if(!stepUp && currentRound == 1){
                            onGameFinished()
                        }
                        if (stepUp){
                            currentRound++
                        }else{
                            currentRound--
                        }
                        roundIndex++
                    }else{
                        currentGuess=0
                        currentCall++
                    }
                }
            )
        }
    }
}
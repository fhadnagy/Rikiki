package com.fonagyma.rikiki

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fonagyma.rikiki.logic.Player
import com.fonagyma.rikiki.ui.components.PlayerGuessPicker
import com.fonagyma.rikiki.ui.theme.RikikiTheme
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RikikiTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //AgeSlider(modifier = Modifier.fillMaxSize(), maxAge = 10)
                    GuessPickerScreen(maxGuess = 10)
                }
            }
        }
    }
}

@Composable
fun LogicController(modifier: Modifier = Modifier.fillMaxSize()) {
    val players = rememberSaveable { mutableStateListOf<Player>()}
    var input by rememberSaveable { mutableStateOf(true)}
    var isGamePlaying by rememberSaveable { mutableStateOf(false)}
    var isEvaluate by rememberSaveable { mutableStateOf(false)}
    var maxRounds by rememberSaveable { mutableStateOf(1)}
    var currentRoundIndex by rememberSaveable { mutableStateOf(0)}
    players.add(Player(0,"dummy"))
    if(input){

    }
}

@Composable
fun GuessPickerScreen(maxGuess: Int) {
    var guess by remember { mutableStateOf(0)}
    var player by remember { mutableStateOf(1)}
    PlayerGuessPicker(
        currentGuess = guess,
        maxGuess = maxGuess,
        playerID = player,
        onNewValue = { newGuess -> guess = newGuess },
        onChosen = {guess=0;player++}
    )
}

@Preview(showBackground = true,
    device = "id:pixel_4"
)
@Composable
fun WelcomeScreenPreview() {
    RikikiTheme {
        WelcomeScreen(modifier = Modifier.fillMaxSize())
    }
}

@Composable
fun WelcomeScreen(modifier: Modifier) {
   Box(
       modifier = modifier.padding(10.dp),
       contentAlignment = Alignment.Center
   ){
       Text(
           text = "Let's play Rikiki!",
           fontSize = 28.sp,
           fontWeight = FontWeight.Bold
       )
   }
}

@Composable
fun AgeSlider(modifier : Modifier, maxAge: Int = 99) {
    var currentAge by remember { mutableStateOf(0) }
    var sliderPosition by remember { mutableStateOf(0f)}
    Column( 
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(
            text = "Choose your age!",
            fontSize = 16.sp,
            modifier = Modifier.padding(5.dp)
        )
        Text(
            text = "$currentAge",
            fontSize = 56.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(5.dp, 24.dp, 5.dp, 5.dp)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            IconButton(onClick = {
                if (currentAge>0){
                    currentAge--
                    sliderPosition=currentAge/maxAge.toFloat()
                }
            }) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "remove"
                )
            }
            IconButton(onClick = {
                if (currentAge<maxAge){
                    currentAge++
                    sliderPosition=currentAge/maxAge.toFloat()
                }
            }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "add"
                )
            }
        }
        Slider(
            modifier = Modifier.padding(16.dp),
            value = sliderPosition,
            onValueChange = { sliderPosition = it ;currentAge = (sliderPosition*maxAge).toInt()},
            steps = maxAge-1
        )
    }
}
package com.fonagyma.rikiki.logic

class Player (
    val ID : Int,
    val name: String,
    val rounds: Int
    ){
    var guesses = Array(rounds){ -1 }
    var handswon =Array(rounds){ -1 }
}


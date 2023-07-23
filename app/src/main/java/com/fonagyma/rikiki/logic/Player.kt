package com.fonagyma.rikiki.logic

class Player (
    val ID : Int,
    val name: String
    ){
    var guesses = mutableListOf<Int>()
    var handswon = mutableListOf<Int>()
}


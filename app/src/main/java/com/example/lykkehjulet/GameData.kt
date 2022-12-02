package com.example.lykkehjulet

data class GameData(
    val lives: Int = 0,
    val points: Int = 0,
) {

    val wheelSpinResult: Int = 0

    val spinBoolean: Boolean = false
    val guessBoolean: Boolean = false
    val guessButtonBoolean: Boolean = false

    var info: String = "Welcom press new game to play"
    var wordToDisplay: String = ""
    var wordToGuess: String = ""

    val guessedChars = ArrayList<Char>()
    val wheelOfValues: Array<Int> =
        arrayOf<Int>(100, 200, 300, 400, 500, 600, 700, 800, 900, 1000, -1)
    val animals: Array<String> = arrayOf<String>("Cow", "Sheep", "Elephant", "Duck", "Zebra")


}



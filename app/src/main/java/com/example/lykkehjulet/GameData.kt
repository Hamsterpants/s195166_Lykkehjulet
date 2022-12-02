package com.example.lykkehjulet

import androidx.compose.runtime.mutableStateOf
import kotlin.random.Random

var randomGenerator = Random(System.currentTimeMillis())

data class GameData(
    val name: String = "Player"
) {
    val lives = mutableStateOf(5)
    val points = mutableStateOf(0)
    private val wheelSpinResult = mutableStateOf(0)

    val spinButtonBoolean = mutableStateOf(false)
    val guessTextFieldBoolean = mutableStateOf(false)
    val guessButtonBoolean = mutableStateOf(false)

    var info = mutableStateOf("Welcome press New Game to play")
    var wordToDisplay = mutableStateOf("")
    private var wordToGuess = mutableStateOf("")
    var wordCategory = mutableStateOf("Category")

    val guessedChars = mutableListOf<Char>()

    private val wheelOfValues: Array<Int> =
        arrayOf(100, 200, 300, 400, 500, 600, 700, 800, 900, 1000, -1)
    private val animals = arrayOf("cow", "sheep", "elephant", "duck", "zebra")


    //Picks random word from array and displays it as *'s
    private fun wordPicker() {
        val x = randomGenerator.nextInt(0, animals.size)
        wordCategory.value = "Animal"
        wordToGuess.value = animals[x]

        for (Char in wordToGuess.value) {
            wordToDisplay.value = wordToDisplay.value + "*"
        }
    }

    //Get random value from the wheel
    fun spinWheel() {
        val x = randomGenerator.nextInt(0, wheelOfValues.size)
        val result = wheelOfValues[x]

        //-1 is bankrupt value of wheel
        if (result == -1) {
            points.value = 0
            info.value = ("You Bankrupt and lose all your points but can still make a guess")
        } else {
            wheelSpinResult.value = result
            info.value = ("You are playing for $result points")
        }
    }

    //When valid guess is made, add to char array and display if it is in the word
    fun guessChar(c: Char) {
        guessedChars.add(c)
        var count = 0
        var newWord = ("")
        for (i in 0 until wordToGuess.value.length) {
            val x = wordToGuess.value[i]
            if (x == c) {
                count++
                newWord = (newWord + c)
            } else {
                newWord = (newWord + wordToDisplay.value[i])
            }
        }

        wordToDisplay.value = newWord
        pointAndLivesSetter(count)
    }

    //Checks hasWon() for correct guess and hasLost() for wrong guess
    private fun pointAndLivesSetter(i: Int) {
        if (i > 0) {
            val pointsWon = wheelSpinResult.value * i
            info.value = "WOW you guessed right and gain $pointsWon points"
            points.value += pointsWon
            hasWon()
        } else {
            info.value = "Wah wah wrong guess -1 life"
            lives.value -= 1
            hasLost()
        }
    }

    //Disables all buttons expect new game and displays message
    private fun hasWon() {
        if (wordToDisplay.value == wordToGuess.value) {
            info.value = "YOU GUESSED THE WORD HURRA, POINTS: ${points.value}"
            spinButtonBoolean.value = false
            guessTextFieldBoolean.value = false
            guessButtonBoolean.value = false
        }
    }
    private fun hasLost() {
        if (lives.value == 0) {
            info.value = "YOU LOST ALL YOUR LIVES :("
            spinButtonBoolean.value = false
            guessTextFieldBoolean.value = false
            guessButtonBoolean.value = false
        }
    }

    //NewGame calls for factory reset on the game
    fun reset() {
        lives.value = 5
        points.value = 0
        wheelSpinResult.value = 0

        spinButtonBoolean.value = true
        guessTextFieldBoolean.value = false
        guessButtonBoolean.value = false

        info.value = "Press Spin to play"
        wordToDisplay.value = ""
        wordPicker()
        wordCategory.value = "Animal"

        guessedChars.clear()
    }
}

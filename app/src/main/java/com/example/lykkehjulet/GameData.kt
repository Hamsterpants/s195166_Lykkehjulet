package com.example.lykkehjulet

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlin.random.Random

data class GameData(
    val name: String = "Player"
) {
    val lives = mutableStateOf(0)
    val points = mutableStateOf(0)
    val wheelSpinResult = mutableStateOf(0)

    val spinBoolean =  mutableStateOf(false)
    val guessBoolean =  mutableStateOf(false)
    val guessButtonBoolean =  mutableStateOf(false)

    var info = mutableStateOf("Welcome press New Game to play")
    var wordToDisplay = mutableStateOf("")
    var wordToGuess = mutableStateOf("")
    var wordCategory = mutableStateOf("Category")

    val guessedChars = mutableListOf<Char>()

    val wheelOfValues: Array<Int> =
        arrayOf<Int>(100, 200, 300, 400, 500, 600, 700, 800, 900, 1000, -1)
    private val animals = arrayOf<String>("Cow", "Sheep", "Elephant", "Duck", "Zebra")


    fun wordPicker(){
        val x = Random.nextInt(0, animals.size)
        wordCategory.value = "Animal"
        wordToGuess.value = animals[x]
        wordToDisplay.value = ""
        for (Char in wordToGuess.value) {
            wordToDisplay.value = wordToDisplay.value + "*"
        }
    }

    fun spinWheel(){
        val x = Random.nextInt(0, wheelOfValues.size)
        val result = wheelOfValues[x]

        if (result == -1){
            points.value = 0
            info.value = ("YOU BUST")
        } else {
            wheelSpinResult.value = result
            info.value = ("You are playing for $result points")
        }
    }

    fun guessChar(c: Char){
        guessedChars.add(c)
        var count = 0;
        var newWord = ("")
        for (boy: Char  in wordToGuess.value) {
            count++
            if (c == boy) {
                newWord = (newWord + c)
            } else {
                newWord = (wordToDisplay.value)
            }
        }



        wordToDisplay.value = newWord
    }


}



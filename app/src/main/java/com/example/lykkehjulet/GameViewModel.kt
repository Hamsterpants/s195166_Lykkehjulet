package com.example.lykkehjulet

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import java.util.*


class GameViewModel : ViewModel() {
    private var gameState: GameData = GameData()

    @Composable
    fun InfoContent() {
        Text(text = gameState.wordCategory.value)
        Text(
            text = gameState.wordToDisplay.value.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(
                    Locale.getDefault()
                ) else it.toString()
            },
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )
        Text(text = gameState.info.value)

        Row(Modifier.padding(20.dp)) {
            Text(text = "Points: ${gameState.points.value}")
            Spacer(Modifier.weight(1f))
            Text(text = "Lives: ${gameState.lives.value}")
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun NewGameButton() {
        AssistChip(
            onClick = {
                gameState.reset()
            },
            label = { Text(text = "New Game") }
        )
    }

    @Composable
    fun SpinButton() {
        Button(
            onClick = {
                gameState.guessTextFieldBoolean.value = true
                gameState.spinButtonBoolean.value = false
                gameState.spinWheel()
            },
            modifier = Modifier.padding(10.dp),
            enabled = gameState.spinButtonBoolean.value
        ) { Text("Spin") }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun GuessTextField() {
        //Validation of textfield from: https://developer.android.com/reference/kotlin/androidx/compose/material3/package-summary#textfield
        var text by rememberSaveable { mutableStateOf("") }
        var isValid by rememberSaveable { mutableStateOf(false) }
        var isGuessed by rememberSaveable { mutableStateOf(false) }
        val regex = "[A-z]".toRegex()

        fun validate(text: String) {

            //isNotEmpty is used since program crash if check firstChar in empty string
            if (text.isNotEmpty()) {
                isGuessed = gameState.guessedChars.contains(
                    text.first().lowercaseChar()
                )
            }

            //Check to validate guess so button is enabled
            isValid = (text.length == 1) && text.matches(regex) && !isGuessed
            gameState.guessButtonBoolean.value = isValid

        }
        TextField(
            value = text,
            onValueChange = {
                text = it
                validate(text)
            },
            singleLine = true,
            label = { Text("Guess") },
            supportingText = {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = if (isGuessed) {
                        "You already guessed that"
                    } else if (isValid) {
                        "Press guess to lock it in"
                    } else {
                        "One letter only"
                    },
                    textAlign = TextAlign.Start,
                )
            },
            enabled = gameState.guessTextFieldBoolean.value
        )

        GuessButton(text)
    }


    @Composable
    fun GuessButton(text: String) {
        Button(
            onClick = {
                gameState.guessButtonBoolean.value = false
                gameState.guessTextFieldBoolean.value = false
                gameState.spinButtonBoolean.value = true
                gameState.guessChar(text.lowercase().first())
            },
            enabled = gameState.guessButtonBoolean.value
        ) { Text("Guess") }
    }

}


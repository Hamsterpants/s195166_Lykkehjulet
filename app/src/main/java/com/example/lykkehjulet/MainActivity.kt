package com.example.lykkehjulet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lykkehjulet.ui.theme.LykkehjuletTheme
import java.lang.Thread.sleep

var gameState = mutableStateOf(GameData())

@ExperimentalMaterial3Api
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LykkehjuletTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GameScreen()
                }
            }
        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameScreen(gameData: GameData = GameData()) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Lykkehjulet")
                },
                actions = {
                    AssistChip(
                        onClick = { /*TODO*/ },
                        label = { Text(text = "New Game") }
                    )
                }
            )
        }
    ) { contentPadding ->
        Box(modifier = Modifier.padding(contentPadding)) {
            Column {

                InfoContent()

                SpinButton()

                Row(
                    Modifier.padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    GuessTextField()
                }
            }
        }
    }
}

@Composable
fun InfoContent() {
    Text(text = "Animal")
    Text(text = "game.value.wordToDisplay")
    Text(text = "game.value.info")

    Row(Modifier.padding(20.dp)) {
        Text(text = "Points: ")
        Spacer(Modifier.weight(1f))
        Text(text = "Lives: ")
    }
}

@Composable
fun SpinButton() {
    Button(
        onClick = {/*TODO*/ },
        modifier = Modifier.padding(10.dp),
        enabled = false
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
        if (text.isNotEmpty()) {
            /*isGuessed = game.value.guessedChars.contains(
                text.first().lowercaseChar()
            )

             */
        }
        isValid =
            (text.length == 1) && text.matches(regex) && !isGuessed
        //game.value.guessButtonBoolean = isValid
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
        enabled = false
    )

    GuessButton(text = text)
}

@Composable
fun GuessButton(text: String) {
    Button(
        onClick = {
            /* TODO */
        },
        enabled = false
    ) { Text("Guess") }
}
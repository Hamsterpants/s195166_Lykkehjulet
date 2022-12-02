package com.example.lykkehjulet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lykkehjulet.ui.theme.LykkehjuletTheme
import java.lang.Thread.sleep

@ExperimentalMaterial3Api
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var testPoints = mutableStateOf(10)
        setContent {
            LykkehjuletTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    Scaffold(
                        topBar = {
                            TopAppBar(
                                title = {
                                    Text(text = "LykkehjuletTitle")
                                },
                                actions = {
                                    AssistChip(
                                        onClick = { /* TODO */ },
                                        label = { Text(text = "new game") }
                                    )
                                }
                            )
                        },
                        floatingActionButton = {
                            FloatingActionButton(
                                onClick = { /*TODO*/ },
                                modifier = Modifier
                                    .size(96.dp).clip(RoundedCornerShape(28.dp))
                            ) {
                                Icon(Icons.Filled.Refresh, contentDescription = "", modifier = Modifier.size(36.dp))
                            }
                        }
                    ) { contentPadding ->
                        Box(modifier = Modifier.padding(contentPadding)) {
                            Column() {
                                Text(text = "Category")
                                Text(text = "Word")
                                Text(text = "This is the info text where info gets displayed wow")
                                Row(Modifier.padding(20.dp)) {
                                    Text(text = "Points: ${testPoints.value}")
                                    Spacer(Modifier.weight(1f))
                                    Text(text = "Lives")
                                }
                            }
                        }
                    }

                    /*Scaffold(
                        floatingActionButton = {
                            FloatingActionButton(onClick = { /*TODO*/ }) {
                                Icon(
                                    imageVector = Icons.Default.Add,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.onPrimaryContainer
                                )
                            }
                        },
                        topBar = {
                            TopAppBar(
                                title = {
                                    Text(text = "Material 3 test")
                                },
                                colors = TopAppBarDefaults.mediumTopAppBarColors(
                                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                                    titleContentColor = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            )
                        }
                    ) { values ->
                        LazyColumn(contentPadding = values) {
                            items(20) {
                                ImageCard(
                                    title = "This is a title",
                                    description = "This is a description below a title, the description should be concrete and descriptive, duh. But also not too long.",
                                    modifier = Modifier.padding(16.dp)
                                )
                            }
                        }
                    }*/
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LykkehjuletTheme {
        Greeting("Android")
    }
}
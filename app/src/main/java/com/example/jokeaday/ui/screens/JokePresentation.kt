package com.example.jokeaday.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.data.dtos.JokeDTO
import com.example.jokeaday.R
import com.example.jokeaday.ui.commonComposables.SpacerSmall
import com.example.jokeaday.ui.theme.DarkGreen
import com.example.jokeaday.ui.theme.Purple40
import com.example.jokeaday.ui.theme.borderRadiusSize
import com.example.jokeaday.ui.theme.customFontFamily
import com.example.jokeaday.ui.theme.fontSizeNormal
import com.example.jokeaday.ui.theme.heightNormal
import com.example.jokeaday.ui.theme.spacingSmall
import com.example.jokeaday.ui.theme.spacingSmallest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JokeDisplay(
    getJoke: () -> Unit, joke: State<JokeDTO?>
) {
    Scaffold(
        containerColor = DarkGreen,
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Purple40,
                    titleContentColor = Color.White
                ),
                title = { Text(text = stringResource(id = R.string.app_name)) }
            )
        },
        bottomBar = {
            BottomAppBar(
                contentColor = Purple40,
                containerColor = Purple40,
                modifier = Modifier.height(heightNormal)
            ){
                TextButton(
                    onClick = getJoke,
                    modifier = Modifier.fillMaxSize(),
                    shape = RoundedCornerShape(0),
                ) {
                    Text(
                        text = stringResource(id = R.string.another_one),
                        color = Color.White,
                        fontFamily = customFontFamily,
                        fontSize = fontSizeNormal,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    ) {
        JokeTextBoxes(
            setup = joke.value?.setup,
            punchline = joke.value?.delivery,
            padding = it,
        )
    }
}

@Composable
fun JokeTextBoxes(padding: PaddingValues, setup: String?, punchline: String?){
    Column(
        modifier = Modifier
            .padding(padding)
            .fillMaxWidth()
    ) {
        val safeSetup = setup ?: "Here's a setup..."
        val safePunchline = punchline ?: "Here's a punchline... Pow! Right in the kisser."

        SpacerSmall()
        TextBox(text = "Setup: $safeSetup")
        Spacer(modifier = Modifier.height(spacingSmallest))
        TextBox(text = "Punchline: $safePunchline")
        Spacer(modifier = Modifier.height(spacingSmallest))
    }
}

@Composable
@Preview
fun TextBox(text: String="") {
    Card(modifier = Modifier
        .padding(horizontal = spacingSmall)
        .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = spacingSmallest
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        shape = RoundedCornerShape(borderRadiusSize)
    ) {
        Text(
            modifier = Modifier.padding(
                vertical = spacingSmall,
                horizontal = spacingSmallest
            ),
            color = Purple40,
            text = text
        )
    }
}
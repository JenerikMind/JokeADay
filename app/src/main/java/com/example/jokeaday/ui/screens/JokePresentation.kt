package com.example.jokeaday.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.data.dtos.JokeDTO
import com.example.jokeaday.ui.reusableComposables.CustomScaffold
import com.example.jokeaday.ui.reusableComposables.SpacerSmall
import com.example.jokeaday.ui.reusableComposables.TextBox
import com.example.jokeaday.ui.theme.spacingSmallest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JokePresentation(
    navController: NavController,
    joke: State<JokeDTO?>,
) {
    CustomScaffold(
        navController = navController,
        saveJoke = { /*TODO*/ }
    ) {
        JokeTextBoxes(
            setup = joke.value?.setup,
            punchline = joke.value?.delivery,
            padding = it,
        )
    }
}

@Composable
fun JokeTextBoxes(padding: PaddingValues, setup: String?, punchline: String?) {
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
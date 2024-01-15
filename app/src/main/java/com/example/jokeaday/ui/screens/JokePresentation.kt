package com.example.jokeaday.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
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
import com.example.data.dtos.JokeDTO
import com.example.jokeaday.R
import com.example.jokeaday.ui.reusableComposables.SpacerSmall
import com.example.jokeaday.ui.reusableComposables.TextBox
import com.example.jokeaday.ui.theme.DarkGreen
import com.example.jokeaday.ui.theme.Purple40
import com.example.jokeaday.ui.theme.customFontFamily
import com.example.jokeaday.ui.theme.fontSizeNormal
import com.example.jokeaday.ui.theme.heightMedium
import com.example.jokeaday.ui.theme.spacingSmallest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JokeDisplay(
    joke: State<JokeDTO?>,
    padding: PaddingValues
) {
    JokeTextBoxes(
        setup = joke.value?.setup,
        punchline = joke.value?.delivery,
        padding = padding,
    )
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
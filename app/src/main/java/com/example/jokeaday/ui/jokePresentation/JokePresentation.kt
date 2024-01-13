package com.example.jokeaday.ui.jokePresentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jokeaday.R
import com.example.jokeaday.ui.theme.DarkGreen
import com.example.jokeaday.ui.theme.Purple40

@Composable
fun JokeDisplay(
    setup: String?, punchline: String?, onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 10.dp)
            .fillMaxWidth()
    ) {
        val safeSetup = setup ?: "Here's a setup..."
        val safePunchline = punchline ?: "Here's a punchline... Pow! Right in the kisser."

        TextBox(text = "Setup: $safeSetup")
        Spacer(modifier = Modifier.height(8.dp))
        TextBox(text = "Punchline: $safePunchline")
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = onClick) {
            Text(stringResource(id = R.string.another_one))
        }
    }
}

@Composable
@Preview
fun TextBox(text: String="") {
    Card(modifier = Modifier
        .padding(horizontal = 16.dp)
        .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Text(
            modifier = Modifier.padding(
                vertical = 16.dp,
                horizontal = 8.dp
            ),
            color = Purple40,
            text = text
        )
    }
}
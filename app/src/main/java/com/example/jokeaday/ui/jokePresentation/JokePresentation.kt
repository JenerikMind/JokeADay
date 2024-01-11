package com.example.jokeaday.ui.jokePresentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jokeaday.R

@Composable
fun JokeDisplay(
    setup: String?,
    punchline: String?,
    onClick: () -> Unit
){
    Column (
        modifier = Modifier
            .padding(horizontal = 10.dp)
            .fillMaxWidth()
    ){
        val safeSetup = setup ?: "Here's a setup..."
        val safePunchline = punchline ?: "Here's a punchline... Pow! Right in the kisser."

        Text(text = "Setup: $safeSetup")
        Text(text = "Punchline: $safePunchline")
        Button(onClick = onClick) {
            Text(stringResource(id = R.string.another_one))
        }
    }
}
package com.example.jokeaday.ui.jokePresentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.jokeaday.R
import com.example.jokeaday.ui.commonComposables.PurpleButton
import com.example.jokeaday.ui.theme.Purple40
import com.example.jokeaday.ui.theme.borderRadiusSize
import com.example.jokeaday.ui.theme.spacingSmall
import com.example.jokeaday.ui.theme.spacingSmallest

@Composable
fun JokeDisplay(
    setup: String?, punchline: String?, onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(horizontal = spacingSmall)
            .fillMaxWidth()
    ) {
        val safeSetup = setup ?: "Here's a setup..."
        val safePunchline = punchline ?: "Here's a punchline... Pow! Right in the kisser."

        TextBox(text = "Setup: $safeSetup")
        Spacer(modifier = Modifier.height(spacingSmallest))
        TextBox(text = "Punchline: $safePunchline")
        Spacer(modifier = Modifier.height(spacingSmallest))
        PurpleButton(onClick=onClick, R.string.another_one)
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
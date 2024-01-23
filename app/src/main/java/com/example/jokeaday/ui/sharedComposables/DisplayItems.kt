package com.example.jokeaday.ui.sharedComposables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.jokeaday.ui.theme.Purple40
import com.example.jokeaday.ui.theme.borderRadiusSize
import com.example.jokeaday.ui.theme.spacingSmall
import com.example.jokeaday.ui.theme.spacingSmallest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun TextBox(text: String = "", onClick: () -> Unit = {}) {
    Card(
        modifier = Modifier
            .padding(horizontal = spacingSmall)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = spacingSmallest
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        shape = RoundedCornerShape(borderRadiusSize),
        onClick = onClick,
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
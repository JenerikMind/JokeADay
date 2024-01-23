package com.example.jokeaday.ui.screens.favorites

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.example.jokeaday.R
import com.example.jokeaday.ui.theme.customFontFamily
import com.example.jokeaday.ui.theme.fontSizeNormal
import com.example.jokeaday.ui.theme.spacingSmall

@Composable
fun CustomSearchBar(
    searchDatabase: (String) -> Unit
) {
    var query by remember { mutableStateOf("") }
    TextField(
        value = query,
        onValueChange = {
            query = it
            searchDatabase(it)
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = spacingSmall)
            .background(color = Color.White),
        textStyle = TextStyle(
            fontFamily = customFontFamily,
            fontSize = fontSizeNormal
        ),
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.search_icon_foreground),
                contentDescription = "search_icon",
                modifier = Modifier.width(60.dp)
            )
        }
    )
}
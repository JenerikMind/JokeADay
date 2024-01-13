package com.example.jokeaday.ui.commonComposables

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.jokeaday.ui.theme.heightSmall

@Composable
fun SpacerSmall(){
    Spacer(modifier = Modifier.height(heightSmall))
}
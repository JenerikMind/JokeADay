package com.example.jokeaday.ui.reusableComposables

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.jokeaday.ui.theme.heightMedium
import com.example.jokeaday.ui.theme.heightSmall
import com.example.jokeaday.ui.theme.spacingSmallest


@Composable
fun SpacerSmallest(){
    Spacer(modifier = Modifier.height(spacingSmallest))
}

@Composable
fun SpacerSmall(){
    Spacer(modifier = Modifier.height(heightSmall))
}

@Composable
fun SpacerMedium(){
    Spacer(modifier = Modifier.height(heightMedium))
}
package com.example.jokeaday.ui.reusableComposables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.jokeaday.R
import com.example.jokeaday.ui.theme.DarkGreen
import com.example.jokeaday.ui.theme.Purple40
import com.example.jokeaday.ui.theme.heightMedium

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomScaffold(
    navController: NavController,
    saveJoke: () -> Unit,
    content: @Composable (padding: PaddingValues) -> Unit
) {
    val currentDestinationIsJoke = navController.currentDestination?.route == "JokePresentation"
    val bottomButtonColors =
        if (currentDestinationIsJoke) listOf(Color.White, Purple40) else listOf(
            Purple40, Color.White
        )

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
                modifier = Modifier
                    .height(heightMedium)
            ) {
                CustomTextButton(
                    onClick = { navController.navigate("JokePresentation") },
                    modifier = Modifier
                        .weight(1f)
                        .background(bottomButtonColors[0]),
                    resId = R.string.joke,
                    textColor = bottomButtonColors[1]
                )
                CustomTextButton(
                    onClick = { navController.navigate("FavoriteJokesPresentation") },
                    modifier = Modifier
                        .weight(1f)
                        .background(bottomButtonColors[1])
                        .fillMaxSize(),
                    resId = R.string.favorites,
                    textColor = bottomButtonColors[0]
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = saveJoke,
                shape = CircleShape
            ) {
                Icon(Icons.Filled.Add, "Save joke to favorites FAB")
            }
        }
    ) {
        content(it)
    }
}
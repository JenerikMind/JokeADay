package com.example.jokeaday.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.data.database.JokeEntity
import com.example.jokeaday.ui.reusableComposables.CustomScaffold
import com.example.jokeaday.ui.reusableComposables.SpacerSmallest
import com.example.jokeaday.ui.reusableComposables.TextBox

@Composable
fun FavoritesPresentation(
    navController: NavController,
    jokes: State<List<JokeEntity>?>
) {
    val jokesList: List<JokeEntity> = jokes.value ?: emptyList()

    CustomScaffold(
        navController = navController,
        saveJoke = {},
    ) {
        Column(modifier = Modifier.padding(it)) {
            SpacerSmallest()
            LazyColumn {
                if (jokesList.isNotEmpty()) {
                    items(jokesList) { joke ->
                        TextBox(
                            text = joke.setup,
                            onClick = { navController.navigate("JokePresentation/${joke.apiId}") }
                        )
                        SpacerSmallest()
                    }
                }
            }
        }
    }
}
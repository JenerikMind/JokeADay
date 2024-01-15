package com.example.jokeaday.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.data.database.JokeEntity
import com.example.jokeaday.ui.reusableComposables.SpacerMedium
import com.example.jokeaday.ui.reusableComposables.SpacerSmallest
import com.example.jokeaday.ui.reusableComposables.TextBox

@Composable
fun FavoriteJokesPresentation(padding: PaddingValues, jokes: State<List<JokeEntity>?>) {
    val jokesList: List<JokeEntity> = jokes.value ?: emptyList()

    Column(modifier = Modifier.padding(padding)) {
        SpacerSmallest()
        LazyColumn {
            if (jokesList.isNotEmpty()){
                items(jokesList){joke ->
                    JokeListItem(setup = joke.setup)
                    SpacerSmallest()
                }
            }
        }
    }
}

@Composable
@Preview
fun JokeListItem(setup: String="Testing some shit..."){
    TextBox(setup)
}
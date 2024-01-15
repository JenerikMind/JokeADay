package com.example.jokeaday.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.tooling.preview.Preview
import com.example.data.database.JokeEntity
import com.example.jokeaday.ui.commonComposables.SpacerMedium
import com.example.jokeaday.ui.commonComposables.SpacerSmall
import com.example.jokeaday.ui.commonComposables.SpacerSmallest
import com.example.jokeaday.ui.commonComposables.TextBox

@Composable
fun FavoriteJokesPresentation(jokes: State<List<JokeEntity>?>) {
    val jokesList: List<JokeEntity> = jokes.value ?: emptyList()

    Column {
        SpacerMedium()
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
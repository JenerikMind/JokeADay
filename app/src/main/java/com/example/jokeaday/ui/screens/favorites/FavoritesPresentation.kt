package com.example.jokeaday.ui.screens.favorites

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.jokeaday.ui.reusableComposables.CustomScaffold
import com.example.jokeaday.ui.reusableComposables.SpacerSmallest
import com.example.jokeaday.ui.reusableComposables.TextBox

@Composable
fun FavoritesPresentation(
    navController: NavController,
) {
    val viewModel = hiltViewModel<FavoritesPresentationViewModel>()
    val jokesList = viewModel.jokesDBLiveData.observeAsState()

    jokesList.value?.isEmpty().let {
        if (it == true) viewModel.getAllJokesFromDB()
    }

    CustomScaffold(
        navController = navController,
        saveJoke = {},
        deleteJoke = {},
        snackbarMessage = viewModel.snackbarMessage
    ) {
        Column(modifier = Modifier.padding(it)) {
            jokesList.value?.let { jokes ->
                if (jokes.isEmpty()) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxHeight()
                    ) {
                        TextBox("You haven't saved any jokes yet.")
                    }
                } else {
                    SpacerSmallest()
                    LazyColumn {
                        items(jokes) { joke ->
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
}

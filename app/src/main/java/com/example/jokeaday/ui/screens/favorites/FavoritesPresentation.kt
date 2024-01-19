package com.example.jokeaday.ui.screens.favorites

import androidx.compose.foundation.layout.Column
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
        deleteJoke = {}
    ) {
        Column(modifier = Modifier.padding(it)) {
            SpacerSmallest()
            LazyColumn {
                jokesList.value?.let {jokes ->
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
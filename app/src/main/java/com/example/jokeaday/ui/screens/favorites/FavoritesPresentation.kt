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
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.jokeaday.R
import com.example.jokeaday.ui.screens.Screen
import com.example.jokeaday.ui.sharedComposables.CustomScaffold
import com.example.jokeaday.ui.sharedComposables.SpacerSmall
import com.example.jokeaday.ui.sharedComposables.SpacerSmallest
import com.example.jokeaday.ui.sharedComposables.TextBox

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
                        TextBox(stringResource(id = R.string.no_jokes_saved))
                    }
                } else {
                    SpacerSmallest()
                    CustomSearchBar(viewModel::searchDatabase)
                    SpacerSmall()
                    LazyColumn {
                        items(jokes) { joke ->
                            TextBox(
                                text = joke.setup ?: joke.joke ?: "Problem pulling from DB",
                                onClick = { navController.navigate("${Screen.Joke.route}/${joke.apiId}") }
                            )
                            SpacerSmallest()
                        }
                    }
                }
            }
        }
    }
}

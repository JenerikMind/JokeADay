package com.example.jokeaday

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.jokeaday.ui.screens.FavoritesPresentation
import com.example.jokeaday.ui.screens.JokePresentation
import com.example.jokeaday.ui.screens.Screen
import com.example.jokeaday.ui.theme.JokeADayTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val vm = hiltViewModel<MainActivityVM>()
            val joke = vm.jokeLiveData.observeAsState()
            val jokesList = vm.jokesDBLiveData.observeAsState()
            val exists = vm.exitsInDB.observeAsState()
            val navController = rememberNavController()

            //TODO: refactor into more appropriate spot
            vm.getAllJokesFromDB()

            JokeADayTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = Screen.Joke.route
                    ) {
                        composable(Screen.Joke.route) {
                            JokePresentation(
                                navController = navController,
                                joke = joke,
                                exists = exists.value ?: false,
                                saveJoke = { vm.saveJoke() }
                            )
                        }
                        composable(
                            "JokePresentation/{uid}",
                            arguments = listOf(navArgument("uid") { type = NavType.IntType })
                        ) {
                            JokePresentation(
                                navController = navController,
                                joke = joke,
                                uid = it.arguments?.getInt("uid"),
                                saveJoke = { vm.saveJoke() }
                            )
                        }
                        composable(Screen.Favorites.route) {
                            FavoritesPresentation(
                                navController = navController,
                                jokes = jokesList,
                            )
                        }
                    }
                }
            }
        }
    }
}
package com.example.jokeaday

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.jokeaday.ui.screens.FavoriteJokesPresentation
import com.example.jokeaday.ui.screens.JokeDisplay
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

            vm.getAllJokesFromDB()

            JokeADayTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                ) {
//                    JokeDisplay(
//                        getJoke = { vm.getJoke() },
//                        joke = joke,
//                        saveJoke = { vm.saveJoke() })

                    FavoriteJokesPresentation(jokesList)
                }
            }
        }
    }
}
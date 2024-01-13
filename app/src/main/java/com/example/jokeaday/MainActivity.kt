package com.example.jokeaday

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.jokeaday.ui.jokePresentation.JokeDisplay
import com.example.jokeaday.ui.theme.DarkGreen
import com.example.jokeaday.ui.theme.JokeADayTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val vm = hiltViewModel<MainActivityVM>()
            val joke = vm.jokeLiveData.observeAsState()

            JokeADayTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                    color = DarkGreen
                ) {
                    JokeDisplay(setup = joke.value?.setup,
                        punchline = joke.value?.delivery,
                        onClick = { vm.getJoke() })
                }
            }
        }
    }
}
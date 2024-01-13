package com.example.jokeaday

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.jokeaday.ui.commonComposables.PurpleButton
import com.example.jokeaday.ui.jokePresentation.JokeDisplay
import com.example.jokeaday.ui.theme.DarkGreen
import com.example.jokeaday.ui.theme.JokeADayTheme
import com.example.jokeaday.ui.theme.Purple40
import com.example.jokeaday.ui.theme.customFontFamily
import com.example.jokeaday.ui.theme.fontSizeNormal
import com.example.jokeaday.ui.theme.heightNormal
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
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
                ) {
                    Scaffold(
                        containerColor = DarkGreen,
                        topBar = {
                            TopAppBar(
                                colors = topAppBarColors(
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
                                modifier = Modifier.height(heightNormal)
                            ){
                                TextButton(
                                    onClick = { vm.getJoke() },
                                    modifier = Modifier.fillMaxSize(),
                                    shape = RoundedCornerShape(0),
                                ) {
                                    Text(
                                        text = stringResource(id = R.string.another_one),
                                        color = Color.White,
                                        fontFamily = customFontFamily,
                                        fontSize = fontSizeNormal,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                        }
                    ) {
                        JokeDisplay(
                            setup = joke.value?.setup,
                            punchline = joke.value?.delivery,
                            padding = it,
                        )
                    }
                }
            }
        }
    }
}
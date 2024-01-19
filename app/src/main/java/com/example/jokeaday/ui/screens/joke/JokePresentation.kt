package com.example.jokeaday.ui.screens.joke

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.jokeaday.R
import com.example.jokeaday.ui.reusableComposables.CustomScaffold
import com.example.jokeaday.ui.reusableComposables.SettingsDrawerSheet
import com.example.jokeaday.ui.reusableComposables.SpacerSmall
import com.example.jokeaday.ui.reusableComposables.TextBox
import com.example.jokeaday.ui.theme.borderRadiusSize
import com.example.jokeaday.ui.theme.customFontFamily
import com.example.jokeaday.ui.theme.fontSizeNormal
import com.example.jokeaday.ui.theme.heightMedium
import com.example.jokeaday.ui.theme.spacingLargest
import com.example.jokeaday.ui.theme.spacingSmall
import com.example.jokeaday.ui.theme.spacingSmallest

@Composable
fun JokePresentation(
    navController: NavController,
    apiId: Int? = null
) {
    val viewModel = hiltViewModel<JokePresentationViewModel>()
    val joke = viewModel.jokeLiveData.observeAsState()

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()

    val isChecked = remember { mutableStateOf(false) }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            SettingsDrawerSheet(
                isChecked = isChecked,
                onCheckedChange = { bool -> isChecked.value = bool })
        },
    ) {
        CustomScaffold(
            navController = navController,
            existsInDb = viewModel.exitsInDB,
            saveJoke = { viewModel.saveJoke() },
            deleteJoke = { viewModel.deleteJoke() },
            coroutineScope = coroutineScope,
            drawerState = drawerState
        ) {
            apiId?.let { apiId ->
                Log.d("JokePresentation", "JokePresentation: Checking api ID $apiId")
                viewModel.getJokeFromDB(apiId)
            }
            JokeTextBoxes(
                setup = joke.value?.setup,
                punchline = joke.value?.delivery,
                padding = it,
                newJoke = { viewModel.getJoke() },
            )
        }
    }
}

@Composable
fun JokeTextBoxes(
    padding: PaddingValues,
    setup: String?,
    punchline: String?,
    newJoke: () -> Unit,
) {
    Column(
        modifier = Modifier
            .padding(padding)
            .fillMaxWidth()
    ) {
        val safeSetup = setup ?: "Here's a setup..."
        val safePunchline = punchline ?: "Here's a punchline... Pow! Right in the kisser."

        SpacerSmall()
        TextBox(text = "Setup: $safeSetup")
        Spacer(modifier = Modifier.height(spacingSmallest))
        TextBox(text = "Punchline: $safePunchline")
        Spacer(modifier = Modifier.height(spacingLargest))

        Button(
            onClick = newJoke,
            modifier = Modifier
                .height(heightMedium)
                .padding(horizontal = spacingSmall)
                .fillMaxWidth(),
            shape = RoundedCornerShape(borderRadiusSize)
        ) {
            Text(
                text = stringResource(id = R.string.another_one),
                fontFamily = customFontFamily,
                fontSize = fontSizeNormal
            )
        }
    }
}
package com.example.jokeaday.ui.screens.joke

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.jokeaday.R
import com.example.jokeaday.ui.sharedComposables.CustomScaffold
import com.example.jokeaday.ui.sharedComposables.SettingsDrawerSheet
import com.example.jokeaday.ui.sharedComposables.SpacerSmall
import com.example.jokeaday.ui.sharedComposables.SpacerSmallest
import com.example.jokeaday.ui.sharedComposables.TextBox
import com.example.jokeaday.ui.theme.borderRadiusSize
import com.example.jokeaday.ui.theme.customFontFamily
import com.example.jokeaday.ui.theme.fontSizeNormal
import com.example.jokeaday.ui.theme.heightMedium
import com.example.jokeaday.ui.theme.spacingSmall

@Composable
fun JokePresentation(
    navController: NavController,
    apiId: Int? = null
) {
    val viewModel = hiltViewModel<JokePresentationViewModel>()
    val joke = viewModel.jokeLiveData.observeAsState()
    val isChecked = viewModel.nsfwIsChecked.observeAsState(false)

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()

    if (apiId == null && joke.value == null) {
        viewModel.getJoke()
    } else {
        if (viewModel.passedApiId != apiId) {
            viewModel.passedApiId = apiId
        } else {
            viewModel.passedApiId = null
        }
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            SettingsDrawerSheet(
                isChecked = isChecked,
                onCheckedChange = { viewModel.toggleNsfwCheckbox(it) })
        },
    ) {
        CustomScaffold(
            navController = navController,
            existsInDb = viewModel.exitsInDB,
            saveJoke = { viewModel.saveJoke() },
            deleteJoke = { viewModel.deleteJoke() },
            coroutineScope = coroutineScope,
            drawerState = drawerState,
            snackbarMessage = viewModel.snackbarMessage
        ) {
            viewModel.passedApiId?.let { apiId ->
                if (apiId != joke.value?.id) {
                    viewModel.getJokeFromDB()
                }
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
            .fillMaxHeight(0.9f),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        val safeSetup = setup ?: "Here's a setup..."
        val safePunchline = punchline ?: "Here's a punchline... Pow! Right in the kisser."

        Box(modifier = Modifier.padding(vertical = spacingSmall)) {
            Column {
                SpacerSmall()
                TextBox(text = "Setup: $safeSetup")
                SpacerSmallest()
                TextBox(text = "Punchline: $safePunchline")
                SpacerSmallest()
            }
        }

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
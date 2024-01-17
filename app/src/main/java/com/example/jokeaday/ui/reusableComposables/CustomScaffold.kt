package com.example.jokeaday.ui.reusableComposables

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.jokeaday.R
import com.example.jokeaday.ui.screens.Screen
import com.example.jokeaday.ui.theme.DarkGreen
import com.example.jokeaday.ui.theme.Purple40
import com.example.jokeaday.ui.theme.heightMedium

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomScaffold(
    navController: NavController,
    saveJoke: () -> Unit,
    exists: Boolean = false,
    content: @Composable (padding: PaddingValues) -> Unit
) {
    val bottomNavItems = listOf(
        Screen.Joke,
        Screen.Favorites
    )
    var jokeExists by remember { mutableStateOf(exists) }

    @Composable
    fun FavoriteIcon(): Painter {
        return if (jokeExists) {
            painterResource(id = R.drawable.favorite_filled)
        } else {
            painterResource(id = R.drawable.favorite_empty)
        }
    }

    fun saveJokeWithIconChange() {
        saveJoke()
        jokeExists = true
    }

    Scaffold(
        containerColor = DarkGreen,
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Purple40,
                    titleContentColor = Color.White
                ),
                title = { Text(text = stringResource(id = R.string.app_name)) }
            )
        },
        bottomBar = {
            NavigationBar(
                modifier = Modifier.height(heightMedium),
                containerColor = Purple40
            ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                bottomNavItems.forEach { screen ->
                    NavigationBarItem(
                        icon = {
                            Icon(
                                painterResource(id = screen.drawId),
                                contentDescription = null
                            )
                        },
                        label = {},
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                    )
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { saveJokeWithIconChange() },
                shape = CircleShape,
                contentColor = Purple40
            ) {
                Icon(FavoriteIcon(), "Save joke to favorites FAB")
            }
        }
    ) {
        content(it)
    }
}
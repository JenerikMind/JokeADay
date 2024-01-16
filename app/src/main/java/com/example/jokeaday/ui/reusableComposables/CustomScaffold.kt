package com.example.jokeaday.ui.reusableComposables

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.jokeaday.R
import com.example.jokeaday.ui.screens.Screen
import com.example.jokeaday.ui.theme.DarkGreen
import com.example.jokeaday.ui.theme.Purple40

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomScaffold(
    navController: NavController,
    saveJoke: () -> Unit,
    content: @Composable (padding: PaddingValues) -> Unit
) {
    val currentDestinationIsJoke = navController.currentDestination?.route == "JokePresentation"
    val bottomButtonColors =
        if (currentDestinationIsJoke) listOf(Color.White, Purple40) else listOf(
            Purple40, Color.White
        )
    val bottomNavItems = listOf(
        Screen.Joke,
        Screen.Favorites
    )

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
            BottomNavigation {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                bottomNavItems.forEach { screen ->
                    BottomNavigationItem(
                        icon = { /*TODO*/ },
                        label = { Text(stringResource(id = screen.resId)) },
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }


//            BottomAppBar(
//                contentColor = Purple40,
//                containerColor = Purple40,
//                modifier = Modifier
//                    .height(heightMedium)
//            ) {
//                CustomTextButton(
//                    onClick = { navController.navigate("JokePresentation") },
//                    modifier = Modifier
//                        .weight(1f)
//                        .background(bottomButtonColors[0]),
//                    resId = R.string.joke,
//                    textColor = bottomButtonColors[1]
//                )
//                CustomTextButton(
//                    onClick = { navController.navigate("FavoritesPresentation") },
//                    modifier = Modifier
//                        .weight(1f)
//                        .background(bottomButtonColors[1])
//                        .fillMaxSize(),
//                    resId = R.string.favorites,
//                    textColor = bottomButtonColors[0]
//                )
//            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = saveJoke,
                shape = CircleShape
            ) {
                Icon(Icons.Filled.Add, "Save joke to favorites FAB")
            }
        }
    ) {
        content(it)
    }
}
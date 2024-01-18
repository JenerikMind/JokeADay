package com.example.jokeaday.ui.reusableComposables

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerState
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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.jokeaday.R
import com.example.jokeaday.ui.screens.Screen
import com.example.jokeaday.ui.theme.DarkGreen
import com.example.jokeaday.ui.theme.Purple40
import com.example.jokeaday.ui.theme.heightMedium
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomScaffold(
    navController: NavController,
    existsInDb: LiveData<Int>? = null,
    saveJoke: (() -> Unit)?,
    deleteJoke: (() -> Unit)?,
    coroutineScope: CoroutineScope? = null,
    drawerState: DrawerState? = null,
    content: @Composable (padding: PaddingValues) -> Unit
) {
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
                title = { Text(text = stringResource(id = R.string.app_name)) },
                actions = {
                    Row {
                        Button(
                            onClick = {
                                coroutineScope?.let {
                                    it.launch {
                                        drawerState?.apply {
                                            if (isClosed) open() else close()
                                        }
                                    }
                                }
                            },
                            shape = CircleShape
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.settings_filled),
                                contentDescription = "Settings",
                                modifier = Modifier.size(35.dp),
                            )
                        }
                    }
                }
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
                            }
                        },
                    )
                }
            }
        },
        floatingActionButton = {
            if (existsInDb != null && saveJoke != null && deleteJoke != null) FavoritesFAB(
                existsInDb,
                saveJoke,
                deleteJoke
            )
        }
    ) {
        content(it)
    }
}

@Composable
fun FavoritesFAB(existsInDb: LiveData<Int>, saveJoke: () -> Unit, deleteJoke: () -> Unit) {
    val iconResId = existsInDb.observeAsState()
    val opToPerform = if (iconResId.value == R.drawable.favorite_empty) saveJoke else deleteJoke
    FloatingActionButton(
        onClick = opToPerform,
        shape = CircleShape,
        contentColor = Purple40
    ) {
        Icon(
            painter = painterResource(id = iconResId.value!!),
            contentDescription = "favorite button"
        )
    }
}
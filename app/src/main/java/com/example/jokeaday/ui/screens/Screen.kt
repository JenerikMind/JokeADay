package com.example.jokeaday.ui.screens

import androidx.annotation.StringRes
import com.example.jokeaday.R

sealed class Screen(val route: String, @StringRes val resId: Int) {
    object Joke : Screen("JokePresentation", R.string.joke)
    object Favorites : Screen("FavoritesPresentation", R.string.favorites)
}
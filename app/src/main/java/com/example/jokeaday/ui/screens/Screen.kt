package com.example.jokeaday.ui.screens

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.jokeaday.R

sealed class Screen(val route: String, @StringRes val resId: Int, @DrawableRes val drawId: Int) {
    object Joke : Screen("JokePresentation", R.string.joke, R.drawable.smile)
    object Favorites : Screen("FavoritesPresentation", R.string.favorites, R.drawable.favorite)
}
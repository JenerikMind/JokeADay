package com.example.jokeaday.ui.screens.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.database.JokeEntity
import com.example.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesPresentationViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {
    private val _jokesDBLiveData = MutableLiveData<List<JokeEntity>>()
    val jokesDBLiveData: LiveData<List<JokeEntity>> = _jokesDBLiveData

    init {
        getAllJokesFromDB()
    }
    private fun getAllJokesFromDB() {
        viewModelScope.launch(Dispatchers.IO) {
            val jokesFlow = repository.getJokesDB()
            jokesFlow.collect {
                if (it.isNotEmpty()) _jokesDBLiveData.postValue(it)
            }
        }
    }
}
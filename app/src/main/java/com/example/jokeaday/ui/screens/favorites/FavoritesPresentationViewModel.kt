package com.example.jokeaday.ui.screens.favorites

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.database.JokeEntity
import com.example.domain.GetJokeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesPresentationViewModel @Inject constructor(
    private val getJokeUseCase: GetJokeUseCase
) : ViewModel() {
    private val _jokesDBLiveData = MutableLiveData<List<JokeEntity>>(emptyList())
    val jokesDBLiveData: LiveData<List<JokeEntity>> = _jokesDBLiveData

    private val _snackbarMessage = MutableLiveData<String?>(null)
    val snackbarMessage: LiveData<String?> = _snackbarMessage

    fun getAllJokesFromDB() {
        viewModelScope.launch(Dispatchers.IO) {
            val jokesFlow = getJokeUseCase.requestAllJokesDb()
            jokesFlow.collect {
                if (it.isNotEmpty()) _jokesDBLiveData.postValue(it)
            }
        }
    }

    fun searchDatabase(query: String) {
        if (query.isNotEmpty()) {
            viewModelScope.launch(Dispatchers.IO) {
                getJokeUseCase.searchBySetupOrDelivery(query).collect {
                    if (it.isNotEmpty()) _jokesDBLiveData.postValue(it)
                }
            }
        }else{
            getAllJokesFromDB()
        }
    }
}
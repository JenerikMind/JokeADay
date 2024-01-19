package com.example.jokeaday.ui.screens.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.database.JokeEntity
import com.example.data.repository.Repository
import com.example.domain.GetJokeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesPresentationViewModel @Inject constructor(
    private val getJokeUseCase: GetJokeUseCase
): ViewModel() {
    private val _jokesDBLiveData = MutableLiveData<List<JokeEntity>>(emptyList())
    val jokesDBLiveData: LiveData<List<JokeEntity>> = _jokesDBLiveData

//    init {
//        jokesDBLiveData.value?.isEmpty().let {
//            if (it == true) getAllJokesFromDB()
//        }
//    }

    fun getAllJokesFromDB() {
        viewModelScope.launch(Dispatchers.IO) {
            val jokesFlow = getJokeUseCase.requestAllJokesDb()
            jokesFlow.collect {
                if (it.isNotEmpty()) _jokesDBLiveData.postValue(it)
            }
        }
    }
}
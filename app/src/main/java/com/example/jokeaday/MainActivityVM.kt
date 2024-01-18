package com.example.jokeaday

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.database.JokeEntity
import com.example.data.dtos.JokeDTO
import com.example.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityVM @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    private val _jokesDBLiveData = MutableLiveData<List<JokeEntity>>()
    val jokesDBLiveData: LiveData<List<JokeEntity>> = _jokesDBLiveData


    fun getAllJokesFromDB() {
        viewModelScope.launch(Dispatchers.IO) {
            val jokesFlow = repository.getJokesDB()
            jokesFlow.collect {
                if (it.isNotEmpty()) _jokesDBLiveData.postValue(it)
            }
        }
    }



    //TODO: refactor into appr. loc


    companion object {
        const val TAG = "MainActivityVM"
    }
}
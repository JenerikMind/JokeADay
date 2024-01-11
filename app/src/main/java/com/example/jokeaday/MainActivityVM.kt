package com.example.jokeaday

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.dtos.JokeDTO
import com.example.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityVM @Inject constructor(
    private val repository: Repository
): ViewModel() {

    private val _jokeLiveData = MutableLiveData<JokeDTO>()
    val jokeLiveData: LiveData<JokeDTO> = _jokeLiveData

    init {
        getJoke()
    }

    fun getJoke(){
        viewModelScope.launch(Dispatchers.IO){
            try {
                val joke = repository.getAJoke()
                Log.d(TAG, "getJoke: Here's the delivery... ${joke.delivery}")
                _jokeLiveData.postValue(joke)
            }catch (e: Exception){
                Log.d(TAG, "getJoke: ${e.message}")
            }
        }
    }

    companion object {
        const val TAG = "MainActivityVM"
    }
}
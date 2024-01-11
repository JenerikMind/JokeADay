package com.example.jokeaday

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityVM @Inject constructor(
    private val repository: Repository
): ViewModel() {

    init {
        getJoke()
    }

    private fun getJoke(){
        viewModelScope.launch(Dispatchers.IO){
            try {
                val joke = repository.getAJoke()
                Log.d(TAG, "getJoke: Here's the delivery... ${joke.delivery}")
            }catch (e: Exception){
                Log.d(TAG, "getJoke: ${e.message}")
            }
        }
    }

    companion object {
        const val TAG = "MainActivityVM"
    }
}
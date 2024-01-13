package com.example.jokeaday

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.dtos.JokeDTO
import com.example.domain.GetAnyJokeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityVM @Inject constructor(
    private val getAnyJokeUseCase: GetAnyJokeUseCase
) : ViewModel() {

    private val _jokeLiveData = MutableLiveData<JokeDTO>()
    val jokeLiveData: LiveData<JokeDTO> = _jokeLiveData

    init {
        getJoke()
    }

    fun getJoke() {
        viewModelScope.launch(Dispatchers.IO) {
            val joke = getAnyJokeUseCase.requestSingleJoke()
            if (joke != null) {
                _jokeLiveData.postValue(joke)
            } else {
                getJoke()
            }
        }
    }

    companion object {
        const val TAG = "MainActivityVM"
    }
}
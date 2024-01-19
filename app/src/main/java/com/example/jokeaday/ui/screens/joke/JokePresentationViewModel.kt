package com.example.jokeaday.ui.screens.joke

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.dtos.JokeDTO
import com.example.domain.DeleteJokeUseCase
import com.example.domain.GetJokeUseCase
import com.example.domain.SaveJokeUseCase
import com.example.jokeaday.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JokePresentationViewModel @Inject constructor(
    private val getJokeUseCase: GetJokeUseCase,
    private val saveJokeUseCase: SaveJokeUseCase,
    private val deleteJokeUseCase: DeleteJokeUseCase,
) : ViewModel() {

    private val _jokeLiveData = MutableLiveData<JokeDTO>()
    val jokeLiveData: LiveData<JokeDTO> = _jokeLiveData

    private val _existsInDB = MutableLiveData<Int>(R.drawable.favorite_empty)
    val exitsInDB: LiveData<Int> = _existsInDB

    private val _nsfwIsChecked = MutableLiveData<Boolean>(false)
    val nsfwIsChecked: LiveData<Boolean> = _nsfwIsChecked

    var passedApiId: Int? = null

    fun getJoke() {
        viewModelScope.launch(Dispatchers.IO) {
            val joke = getJokeUseCase.requestSingleJoke(nsfwIsChecked.value!!)
            if (joke != null) {
                _jokeLiveData.postValue(joke)
                favIconCheck()
            } else {
                getJoke()
            }
        }
    }

    fun saveJoke() {
        jokeLiveData.value?.let {
            viewModelScope.launch(Dispatchers.IO) {
                saveJokeUseCase.saveJoke(it)
                favIconCheck()
            }
        }
    }

    fun deleteJoke() {
        viewModelScope.launch(Dispatchers.IO) {
            jokeLiveData.value?.let {
                deleteJokeUseCase.deleteJoke(it.id)
            }
        }
    }

    fun getJokeFromDB() {
        passedApiId?.let { passedId ->
            viewModelScope.launch(Dispatchers.IO) {
                getJokeUseCase.requestJokeDb(passedId)?.let {
                    _jokeLiveData.postValue(it)
                    favIconCheck()
                }
            }
        }
    }

    private suspend fun checkIfExists(): Boolean {
        val asyncCheck = viewModelScope.async(Dispatchers.IO) {
            jokeLiveData.value?.let {
                return@async getJokeUseCase.checkIfJokeExists(it.id)
            }
        }
        return asyncCheck.await() == 1
    }

    private suspend fun favIconCheck() {
        if (checkIfExists()) {
            _existsInDB.postValue(R.drawable.favorite_filled)
        } else {
            _existsInDB.postValue(R.drawable.favorite_empty)
        }
    }

    fun toggleNsfwCheckbox(toggled: Boolean) {
        _nsfwIsChecked.postValue(toggled)
    }

}
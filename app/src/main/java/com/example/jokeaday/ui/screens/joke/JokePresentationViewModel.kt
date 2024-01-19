package com.example.jokeaday.ui.screens.joke

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.dtos.JokeDTO
import com.example.data.repository.Repository
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

    init {
        getJoke()
    }
    

    fun getJoke() {
        viewModelScope.launch(Dispatchers.IO) {
            val joke = getJokeUseCase.requestSingleJoke(nsfwIsChecked.value!!)
            if (joke != null) {
                _jokeLiveData.postValue(joke)
                if (checkIfExists()) {
                    _existsInDB.postValue(R.drawable.favorite_filled)
                } else {
                    _existsInDB.postValue(R.drawable.favorite_empty)
                }
            } else {
                getJoke()
            }
        }
    }

    fun saveJoke() {
        jokeLiveData.value?.let {
            viewModelScope.launch(Dispatchers.IO) {
                saveJokeUseCase.saveJoke(it)
            }
            _existsInDB.postValue(R.drawable.favorite_filled)
        }
    }

    fun deleteJoke() {
        viewModelScope.launch(Dispatchers.IO) {
            jokeLiveData.value?.let {
                deleteJokeUseCase.deleteJoke(it.id)
            }
        }
    }

    fun getJokeFromDB(apiId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            getJokeUseCase.requestJokeDb(apiId)?.let {
                _jokeLiveData.postValue(it)
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

    fun toggleNsfwCheckbox(toggled: Boolean) {
        _nsfwIsChecked.postValue(toggled)
    }

}
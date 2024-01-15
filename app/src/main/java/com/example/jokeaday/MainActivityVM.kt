package com.example.jokeaday

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.database.JokeEntity
import com.example.data.dtos.JokeDTO
import com.example.data.repository.Repository
import com.example.domain.GetAnyJokeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityVM @Inject constructor(
    private val getAnyJokeUseCase: GetAnyJokeUseCase,
    private val repository: Repository
) : ViewModel() {

    private val _jokeLiveData = MutableLiveData<JokeDTO>()
    val jokeLiveData: LiveData<JokeDTO> = _jokeLiveData

    private val _jokesDBLiveData = MutableLiveData<List<JokeEntity>>()
    val jokesDBLiveData: LiveData<List<JokeEntity>> = _jokesDBLiveData

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

    fun saveJoke() {
        jokeLiveData.value?.let {
            val jokeEntity = JokeEntity(
                apiId = it.id,
                category = it.category,
                setup = it.setup,
                delivery = it.delivery,
                type = it.type,
                safe = it.safe,
                lang = it.lang,
                flags = it.flags.toString(),
            )

            viewModelScope.launch(Dispatchers.IO){
                repository.insertJokeDB(jokeEntity)
            }
        }
    }

    fun getAllJokesFromDB(){
        viewModelScope.launch(Dispatchers.IO) {
            val jokesFlow = repository.getJokes()
            jokesFlow.collect {
                if (it.isNotEmpty()) _jokesDBLiveData.postValue(it)
            }
        }
    }

    companion object {
        const val TAG = "MainActivityVM"
    }
}
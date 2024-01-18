package com.example.jokeaday

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.database.JokeEntity
import com.example.data.dtos.JokeDTO
import com.example.data.repository.Repository
import com.example.domain.CheckIfExistsInDBUseCase
import com.example.domain.GetAnyJokeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityVM @Inject constructor(
    private val getAnyJokeUseCase: GetAnyJokeUseCase,
    private val checkIfExistsInDBUseCase: CheckIfExistsInDBUseCase,
    private val repository: Repository
) : ViewModel() {

    private val _jokeLiveData = MutableLiveData<JokeDTO>()
    val jokeLiveData: LiveData<JokeDTO> = _jokeLiveData

    private val _existsInDB = MutableLiveData<Int>(R.drawable.favorite_empty)
    val exitsInDB: LiveData<Int> = _existsInDB

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

            viewModelScope.launch(Dispatchers.IO) {
                repository.insertJokeDB(jokeEntity)
            }

            _existsInDB.postValue(R.drawable.favorite_filled)
        }
    }

    fun getAllJokesFromDB() {
        viewModelScope.launch(Dispatchers.IO) {
            val jokesFlow = repository.getJokesDB()
            jokesFlow.collect {
                if (it.isNotEmpty()) _jokesDBLiveData.postValue(it)
            }
        }
    }

    suspend fun checkIfExists(): Boolean {
        val asyncCheck = viewModelScope.async(Dispatchers.IO) {
            jokeLiveData.value?.let {
                return@async checkIfExistsInDBUseCase.check(it.id)
            }
        }
        return asyncCheck.await() == 1
    }

    fun getJokeFromDB(apiId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            Log.d(TAG, "getJokeFromDB: Attempting to pull $apiId from DB")
            val joke = repository.getJokeDB(apiId)
            joke.collect {
                it?.let {
                    _jokeLiveData.postValue(it.convertIntoDTO())
                }
            }
        }
    }

    //TODO: refactor into appr. loc
    fun JokeEntity.convertIntoDTO(): JokeDTO {
        return JokeDTO(
            error = "false",
            category = this.category,
            setup = this.setup,
            delivery = this.delivery,
            type = this.type,
            id = this.apiId,
            lang = this.lang,
            safe = this.safe
        )
    }

    companion object {
        const val TAG = "MainActivityVM"
    }
}
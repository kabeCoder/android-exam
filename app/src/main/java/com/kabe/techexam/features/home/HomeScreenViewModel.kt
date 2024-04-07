package com.kabe.techexam.features.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kabe.techexam.data.base.Status
import com.kabe.techexam.domain.RandomPerson
import com.kabe.techexam.data.repository.RandomPersonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val randomPersonRepository: RandomPersonRepository
) : ViewModel() {

    private val _errorMessage = MutableSharedFlow<String?>()
    val errorMessage: SharedFlow<String?> = _errorMessage.asSharedFlow()

    private val _randomPerson = MutableSharedFlow<List<RandomPerson>>()
    val randomPerson: SharedFlow<List<RandomPerson>> = _randomPerson.asSharedFlow()

    private val _loadingStateRandomPerson = MutableStateFlow(false)
    val loadingStateRandomPerson: StateFlow<Boolean> = _loadingStateRandomPerson

    private var isRandomPersonFetched = false

    init {
        viewModelScope.launch {
            val cachedData = randomPersonRepository.getCachedRandomPerson()
            if (cachedData.isNotEmpty()) {
                _randomPerson.emit(cachedData)
                isRandomPersonFetched = true
            }
        }
    }

    suspend fun getRandomPerson() {
        if (!isRandomPersonFetched) {
            _loadingStateRandomPerson.emit(true)
            val randomPersonResult = randomPersonRepository.getRandomPerson()
            when(randomPersonResult.status) {
                Status.SUCCESS -> {
                    randomPersonResult.data?.let { randomPerson ->
                        _randomPerson.emit(randomPerson)
                        isRandomPersonFetched = true
                        _loadingStateRandomPerson.emit(false)
                    }
                }
                Status.ERROR -> {
                    _loadingStateRandomPerson.emit(false)
                    randomPersonResult.message?.let { _errorMessage.emit(it) }
                }
            }
        } else {
            _randomPerson.emit(randomPersonRepository.getCachedRandomPerson())
            _loadingStateRandomPerson.emit(false)
        }
    }
}

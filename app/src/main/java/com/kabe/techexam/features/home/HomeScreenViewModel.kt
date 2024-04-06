package com.kabe.techexam.features.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kabe.techexam.data.base.Status
import com.kabe.techexam.domain.RandomPerson
import com.kabe.techexam.repository.RandomPersonRepository
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

    private val randomPersonList = mutableListOf<RandomPerson>()
    private val _randomPerson= MutableSharedFlow<MutableList<RandomPerson>>()
    val randomPerson: SharedFlow<List<RandomPerson>> = _randomPerson.asSharedFlow()

    private val _loadingStateRandomPerson = MutableStateFlow(false)
    val loadingStateRandomPerson: StateFlow<Boolean> = _loadingStateRandomPerson

    suspend fun getRandomPerson() {
        _loadingStateRandomPerson.emit(true)
        viewModelScope.launch {
            val randomPersonResult = randomPersonRepository.getRandomPerson()
            when(randomPersonResult.status) {
                Status.SUCCESS -> randomPersonResult.data?.let { randomPerson ->
                    _loadingStateRandomPerson.emit(false)
                    _randomPerson.emit(randomPerson.toMutableList())
                    Log.d("HomeScreenViewModel", "Success - Dumaan")
                }
                Status.ERROR -> {
                    _loadingStateRandomPerson.emit(false)
                    randomPersonResult.message?.let { _errorMessage.emit(it) }
                    Log.d("HomeScreenViewModel", "Failed - Dumaan")
                }
            }
        }
    }
}
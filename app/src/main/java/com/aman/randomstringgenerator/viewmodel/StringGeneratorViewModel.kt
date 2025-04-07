package com.aman.randomstringgenerator.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aman.randomstringgenerator.data.RandomStringRepository
import com.aman.randomstringgenerator.data.local.RandomStringEntity
import com.aman.randomstringgenerator.helper.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class UIState<out T> {
    object Loading : UIState<Nothing>()
    data class Success<T>(val data: T) : UIState<T>()
    data class Failure(val throwable: Throwable) : UIState<Nothing>()
}

@HiltViewModel
class StringGeneratorViewModel @Inject constructor(
    private val repository: RandomStringRepository,
    private val dispatcherProvider: DispatcherProvider
) : ViewModel() {

    val allStrings: StateFlow<UIState<List<RandomStringEntity>>> =
        repository.getStrings()
            .map<List<RandomStringEntity>, UIState<List<RandomStringEntity>>> { UIState.Success(it) }
            .catch { emit(UIState.Failure(it)) }
            .stateIn(viewModelScope, SharingStarted.Lazily, UIState.Loading)

    private val _generateState = MutableStateFlow<UIState<Unit>>(UIState.Success(Unit))
    val generateState: StateFlow<UIState<Unit>> = _generateState

    fun fetchRandomString(length: Int) {
        viewModelScope.launch(dispatcherProvider.io) {
            _generateState.value = UIState.Loading
            runCatching {
                repository.fetchAndSaveRandomString(length)
            }.onSuccess {
                _generateState.value = UIState.Success(Unit)
            }.onFailure {
                _generateState.value = UIState.Failure(it)
            }
        }
    }



    fun deleteString(id: Int) {
        viewModelScope.launch(dispatcherProvider.io) {
            repository.deleteStringById(id)
        }
    }

    fun deleteAll() {
        viewModelScope.launch(dispatcherProvider.io) {
            repository.deleteAll()
        }
    }
}
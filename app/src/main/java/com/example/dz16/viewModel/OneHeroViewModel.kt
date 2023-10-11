package com.example.dz16.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dz16.models.Character
import com.example.dz16.request.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OneHeroViewModel @Inject constructor(val repository: Repository) : ViewModel() {
    private val _uistate = MutableLiveData<UiState>(UiState.Empty)
    val uiState: LiveData<UiState> = _uistate

    init {
        getData(getRandomId().toString())
    }

    fun getData(id: String) {
        _uistate.value = UiState.Loading
        viewModelScope.launch {
            val character = repository.getHeroFromId(id)
            _uistate.postValue(UiState.Success(character = character))
        }
    }

    private fun getRandomId() = (1..100).random()

    sealed class UiState {
        object Empty : UiState()
        object Loading : UiState()
        class Success(val character: Character) : UiState()
        class Error(val error: String) : UiState()
    }
}
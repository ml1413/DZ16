package com.example.dz16.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dz16.models.Character
import com.example.dz16.request.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.log

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
            if (character.isSuccessful) {
                _uistate.postValue(character.body()?.let { UiState.Success(character = it) })
            } else {
                _uistate.postValue(UiState.Loading)
                val idPlus = id.toInt() + 1
                getData(idPlus.toString())
            }

        }
    }

    private fun getRandomId() = (1..500).random()

    sealed class UiState {
        object Empty : UiState()
        object Loading : UiState()
        class Success(val character: Character) : UiState()
        class Error(val error: String) : UiState()
    }
}
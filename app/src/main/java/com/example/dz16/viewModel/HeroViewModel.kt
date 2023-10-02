package com.example.dz16.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.util.Util
import com.example.dz16.App
import com.example.dz16.models.Character
import com.example.dz16.request.Repository
import com.example.dz16.utils.onOffProgressBar
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HeroViewModel @Inject constructor(val repository: Repository) : ViewModel() {
    private val _uiState = MutableLiveData<UiState>(UiState.Empty)
    val uiState: LiveData<UiState> = _uiState
    init {
        getData()
    }

    fun getData() {
        _uiState.value = UiState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            val listHero = repository.getAllHero()
            _uiState.postValue(UiState.Result(list = listHero))
        }
    }

    sealed class UiState() {
        object Empty : UiState()
        object Loading : UiState()
        class Result(val list: List<Character>) : UiState()
        class Error(val error: String) : UiState()
    }
}
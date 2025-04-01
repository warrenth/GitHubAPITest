package com.kth.githubapi.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kth.githubapi.domain.usecase.GetRepositoriesUseCase
import com.kth.githubapi.presentation.state.RepositoriesUiState
import com.kth.githubapi.presentation.state.toUiItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RepositoriesViewModel @Inject constructor(
    private val getRepositories: GetRepositoriesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<RepositoriesUiState>(RepositoriesUiState.Loading)
    val uiState: StateFlow<RepositoriesUiState> = _uiState.asStateFlow()

    private val _errorFlow = MutableSharedFlow<Throwable>()
    val errorFlow = _errorFlow.asSharedFlow()

    fun load(user: String) {
        viewModelScope.launch {
            getRepositories(user)
                .map { repos -> RepositoriesUiState.Loaded(repos.map { it.toUiItem() }) }
                .catch { _errorFlow.emit(it) }
                .collect { uiState -> _uiState.value = uiState }
        }
    }
}
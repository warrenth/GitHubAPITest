package com.kth.githubapi.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kth.githubapi.domain.usecase.GetBannerUseCase
import com.kth.githubapi.domain.usecase.GetIssuesUseCase
import com.kth.githubapi.presentation.mapper.toUiItems
import com.kth.githubapi.presentation.state.IssuesUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IssuesViewModel @Inject constructor(
    private val getIssues: GetIssuesUseCase,
    private val getBanner: GetBannerUseCase
) : ViewModel() {

    private val _errorFlow = MutableSharedFlow<Throwable>()
    val errorFlow = _errorFlow.asSharedFlow()

    private val _uiState = MutableStateFlow<IssuesUiState>(IssuesUiState.Loading)
    val uiState: StateFlow<IssuesUiState> = _uiState.asStateFlow()


    fun loadIssues(user: String, repo: String) {
        viewModelScope.launch {
            combine(
                getIssues(user, repo),
                getBanner()
            ) { issues, banner ->
                IssuesUiState.Loaded(issues.toUiItems().toPersistentList())
            }
                .catch { _errorFlow.emit(it) }
                .collect { _uiState.value = it }
        }
    }
}
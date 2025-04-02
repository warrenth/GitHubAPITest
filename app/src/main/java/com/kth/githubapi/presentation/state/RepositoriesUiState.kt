package com.kth.githubapi.presentation.state

sealed interface RepositoriesUiState {
    data object Loading : RepositoriesUiState
    data object Empty : RepositoriesUiState
    data class Loaded(val items: List<RepositoryUiItem>) : RepositoriesUiState
}
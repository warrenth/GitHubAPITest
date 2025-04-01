package com.kth.githubapi.presentation.state

sealed interface RepositoriesUiState {
    object Loading : RepositoriesUiState
    data class Loaded(val items: List<RepositoryUiItem>) : RepositoriesUiState
}
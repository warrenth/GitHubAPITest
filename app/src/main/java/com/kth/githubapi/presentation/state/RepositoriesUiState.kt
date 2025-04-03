package com.kth.githubapi.presentation.state


import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import kotlinx.collections.immutable.ImmutableList

@Stable
sealed interface RepositoriesUiState {
    data object Loading : RepositoriesUiState
    data object Empty : RepositoriesUiState
    @Immutable
    data class Loaded(val items: ImmutableList<RepositoryUiItem>) : RepositoriesUiState
}

@Stable
sealed interface RepositoriesUiEffect {
    @Immutable
    data class ShowErrorDialog(val message: String) : RepositoriesUiEffect
}
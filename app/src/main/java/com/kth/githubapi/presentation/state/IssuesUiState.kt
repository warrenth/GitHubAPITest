package com.kth.githubapi.presentation.state

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import kotlinx.collections.immutable.ImmutableList

@Stable
sealed class IssuesUiState {
    @Immutable
    data object Loading : IssuesUiState()
    @Immutable
    data class Loaded(val items: ImmutableList<IssueUiItem>) : IssuesUiState()
}
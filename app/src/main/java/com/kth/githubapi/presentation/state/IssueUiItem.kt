package com.kth.githubapi.presentation.state

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable

@Stable
sealed class IssueUiItem {
    @Immutable
    data class IssueItem(val number: Int, val title: String) : IssueUiItem()
    @Immutable
    data class BannerItem(val imageUrl: String) : IssueUiItem()
}
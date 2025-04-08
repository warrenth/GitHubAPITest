package com.kth.githubapi.presentation.mapper

import com.kth.githubapi.domain.model.Banner
import com.kth.githubapi.domain.model.Issue
import com.kth.githubapi.presentation.state.IssueUiItem

fun List<Issue>.toUiItems(): List<IssueUiItem.IssueItem> {
    return this.map { IssueUiItem.IssueItem(it.number, it.title) }
}

fun List<IssueUiItem>.insertBannerAt(
    index: Int,
    banner: Banner
): List<IssueUiItem> {
    return toMutableList().apply {
        if (index in indices) {
            add(index, IssueUiItem.BannerItem(banner.imageUrl))
        }
    }
}

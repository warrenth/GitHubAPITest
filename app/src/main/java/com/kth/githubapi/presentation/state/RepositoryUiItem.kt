package com.kth.githubapi.presentation.state

import com.kth.githubapi.domain.model.Repository

data class RepositoryUiItem(
    val id: Int,
    val name: String,
    val url: String,
    val description: String?,
    val language: String?,
    val starCount: Int
)

fun Repository.toUiItem(): RepositoryUiItem? {

    if (id <= 0 || name.isBlank()) return null

    return RepositoryUiItem(
        id = id,
        name = name,
        url = url,
        description = description,
        language = language,
        starCount = starCount
    )
}
package com.kth.githubapi.data.remote.model

import com.kth.githubapi.domain.model.Repository
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class RepositoryResponse(
    val id: Int,
    val name: String,
    @SerialName("html_url")
    val htmlUrl: String,
    val description: String? = null,
    val language: String? = null,
    @SerialName("stargazers_count")
    val starCount: Int
)

fun RepositoryResponse.toDomain(): Repository {
    return Repository(
        id = id,
        name = name,
        url = htmlUrl,
        description = description,
        language = language,
        starCount = starCount
    )
}

fun List<RepositoryResponse>.toDomainList(): List<Repository> {
    return map { it.toDomain() }
}
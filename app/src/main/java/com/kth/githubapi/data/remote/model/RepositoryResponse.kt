package com.kth.githubapi.data.remote.model

import com.kth.githubapi.domain.model.Repository
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class RepositoryResponse(
    @SerialName("id") val id: Int = 0,
    @SerialName("name") val name: String = "",
    @SerialName("html_url") val htmlUrl: String = "",
    @SerialName("description") val description: String? = null,
    @SerialName("language") val language: String? = null,
    @SerialName("stargazers_count") val starCount: Int = 0
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
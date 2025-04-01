package com.kth.githubapi.data.remote.model

import com.kth.githubapi.domain.model.Issue
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class IssueResponse(
    @SerialName("number") val number: Int?,
    @SerialName("title") val title: String?
)

fun IssueResponse.toDomainModel(): Issue = Issue(
    number = this.number ?: -1,
    title = this.title ?: "제목 없음"
)

fun List<IssueResponse>.toDomainModels(): List<Issue> {
    return this.map { it.toDomainModel() }
}
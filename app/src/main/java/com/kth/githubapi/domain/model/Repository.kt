package com.kth.githubapi.domain.model

data class Repository(
    val id: Int,
    val name: String,
    val url: String,
    val description: String?,
    val language: String?,
    val starCount: Int
)

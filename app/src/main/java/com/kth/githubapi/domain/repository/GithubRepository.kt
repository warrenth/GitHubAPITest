package com.kth.githubapi.domain.repository

import com.kth.githubapi.domain.model.Repository

interface GithubRepository {
    suspend fun fetchRepositories(user: String): List<Repository>
}

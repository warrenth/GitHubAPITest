package com.kth.githubapi.domain.repository

import com.kth.githubapi.domain.model.Repository

interface RepositoriesRepository {
    suspend fun fetchRepositories(user: String): List<Repository>
}

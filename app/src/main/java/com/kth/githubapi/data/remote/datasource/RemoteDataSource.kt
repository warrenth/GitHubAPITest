package com.kth.githubapi.data.remote.datasource

import com.kth.githubapi.domain.model.Issue
import com.kth.githubapi.domain.model.Repository

interface RemoteDataSource {
    suspend fun getIssues(user: String, repo: String): List<Issue>
    suspend fun getRepositories(user: String): List<Repository>
}
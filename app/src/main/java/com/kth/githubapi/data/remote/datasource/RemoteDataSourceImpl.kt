package com.kth.githubapi.data.remote.datasource

import com.kth.githubapi.data.remote.api.GitHubApi
import com.kth.githubapi.data.remote.model.toDomainList
import com.kth.githubapi.data.remote.model.toDomainModels
import com.kth.githubapi.domain.model.Issue
import com.kth.githubapi.domain.model.Repository
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val api: GitHubApi
) : RemoteDataSource {
    override suspend fun getIssues(user: String, repo: String): List<Issue> {
        return api.getIssues(user, repo).toDomainModels()
    }
    override suspend fun getRepositories(user: String): List<Repository> {
        return api.getRepositories(user).toDomainList()
    }
}
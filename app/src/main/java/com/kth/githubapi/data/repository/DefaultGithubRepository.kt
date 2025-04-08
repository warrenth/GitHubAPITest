package com.kth.githubapi.data.repository

import com.kth.githubapi.data.remote.datasource.RemoteDataSource
import com.kth.githubapi.domain.model.Repository
import com.kth.githubapi.domain.repository.GithubRepository
import javax.inject.Inject

class DefaultGithubRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : GithubRepository {
    override suspend fun fetchRepositories(user: String): List<Repository> {
        return remoteDataSource.getRepositories(user)
    }
}
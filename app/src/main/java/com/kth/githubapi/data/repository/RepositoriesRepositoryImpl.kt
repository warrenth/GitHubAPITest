package com.kth.githubapi.data.repository

import com.kth.githubapi.data.remote.datasource.RemoteDataSource
import com.kth.githubapi.domain.model.Repository
import com.kth.githubapi.domain.repository.RepositoriesRepository
import javax.inject.Inject

class RepositoriesRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : RepositoriesRepository {
    override suspend fun fetchRepositories(user: String): List<Repository> {
        return remoteDataSource.getRepositories(user)
    }
}
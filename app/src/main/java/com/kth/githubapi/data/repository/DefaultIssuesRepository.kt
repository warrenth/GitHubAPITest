package com.kth.githubapi.data.repository

import com.kth.githubapi.data.remote.datasource.RemoteDataSource
import com.kth.githubapi.domain.model.Issue
import com.kth.githubapi.domain.repository.IssuesRepository
import javax.inject.Inject

class DefaultIssuesRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : IssuesRepository {
    override suspend fun fetchIssues(user: String, repo: String): List<Issue> {
        return remoteDataSource.getIssues(user, repo)
    }
}
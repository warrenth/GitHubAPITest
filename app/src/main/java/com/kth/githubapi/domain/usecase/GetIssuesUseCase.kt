package com.kth.githubapi.domain.usecase

import com.kth.githubapi.domain.model.Issue
import com.kth.githubapi.domain.repository.IssuesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetIssuesUseCase @Inject constructor(
    private val repository: IssuesRepository
) {
    operator fun invoke(user: String, repo: String): Flow<List<Issue>> = flow {
        emit(repository.fetchIssues(user, repo))
    }.flowOn(Dispatchers.IO)
}
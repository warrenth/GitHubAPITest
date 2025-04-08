package com.kth.githubapi.domain.usecase

import com.kth.githubapi.domain.model.Repository
import com.kth.githubapi.domain.repository.GithubRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetRepositoriesUseCase @Inject constructor(
    private val repository: GithubRepository
) {
    operator fun invoke(user: String): Flow<List<Repository>> = flow {
        emit(repository.fetchRepositories(user))
    }.flowOn(Dispatchers.IO)
}
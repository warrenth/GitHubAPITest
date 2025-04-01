package com.kth.githubapi.domain.usecase

import com.kth.githubapi.domain.model.Repository
import com.kth.githubapi.domain.repository.RepositoriesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetRepositoriesUseCase @Inject constructor(
    private val repository: RepositoriesRepository
) {
    operator fun invoke(user: String): Flow<List<Repository>> = flow {
        emit(repository.fetchRepositories(user))
    }.flowOn(Dispatchers.IO)
}
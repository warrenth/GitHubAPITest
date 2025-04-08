package com.kth.githubapi.domain.usecase

import com.kth.githubapi.domain.model.Banner
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetBannerUseCase @Inject constructor() {
    operator fun invoke(): Flow<Banner> = flow {
        emit(Banner(""))
    }
}
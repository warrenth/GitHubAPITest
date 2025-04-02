package com.kth.githubapi.data.di

import com.kth.githubapi.data.remote.datasource.RemoteDataSource
import com.kth.githubapi.data.remote.datasource.RemoteDataSourceImpl
import com.kth.githubapi.data.repository.IssuesRepositoryImpl
import com.kth.githubapi.data.repository.RepositoriesRepositoryImpl
import com.kth.githubapi.domain.repository.IssuesRepository
import com.kth.githubapi.domain.repository.RepositoriesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindRemoteDataSource(impl: RemoteDataSourceImpl): RemoteDataSource

    @Binds
    abstract fun bindIssuesRepository(impl: IssuesRepositoryImpl): IssuesRepository

    @Binds
    abstract fun bindRepositoryRepository(impl: RepositoriesRepositoryImpl): RepositoriesRepository

}
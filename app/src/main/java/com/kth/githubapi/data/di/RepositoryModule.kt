package com.kth.githubapi.data.di

import com.kth.githubapi.data.remote.datasource.RemoteDataSource
import com.kth.githubapi.data.remote.datasource.RemoteDataSourceImpl
import com.kth.githubapi.data.repository.DefaultGithubRepository
import com.kth.githubapi.data.repository.DefaultIssuesRepository
import com.kth.githubapi.domain.repository.GithubRepository
import com.kth.githubapi.domain.repository.IssuesRepository
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
    abstract fun bindIssuesRepository(impl: DefaultIssuesRepository): IssuesRepository

    @Binds
    abstract fun bindRepositoryRepository(impl: DefaultGithubRepository): GithubRepository

}
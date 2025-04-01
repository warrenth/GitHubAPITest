package com.kth.githubapi.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.kth.githubapi.data.remote.api.GitHubApi
import com.kth.githubapi.data.repository.IssuesRepositoryImpl
import com.kth.githubapi.data.remote.datasource.RemoteDataSource
import com.kth.githubapi.data.remote.datasource.RemoteDataSourceImpl
import com.kth.githubapi.data.repository.RepositoriesRepositoryImpl
import com.kth.githubapi.domain.repository.IssuesRepository
import com.kth.githubapi.domain.repository.RepositoriesRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class HiltModule {

    @Binds
    abstract fun bindRemoteDataSource(impl: RemoteDataSourceImpl): RemoteDataSource

    @Binds
    abstract fun bindIssuesRepository(impl: IssuesRepositoryImpl): IssuesRepository

    @Binds
    abstract fun bindRepositoryRepository(impl: RepositoriesRepositoryImpl): RepositoriesRepository

    companion object {
        @Provides
        @Singleton
        fun provideGithubApi(): GitHubApi {
            val contentType = "application/json".toMediaType()
            val json = Json { ignoreUnknownKeys = true }

            return Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(json.asConverterFactory(contentType))
                .build()
                .create(GitHubApi::class.java)
        }
    }

}
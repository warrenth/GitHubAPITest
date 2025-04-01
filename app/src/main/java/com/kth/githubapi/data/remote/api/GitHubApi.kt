package com.kth.githubapi.data.remote.api

import com.kth.githubapi.data.remote.model.IssueResponse
import com.kth.githubapi.data.remote.model.RepositoryResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubApi {
    @GET("users/{user}/repos")
    suspend fun getRepositories(
        @Path("user") user: String,
        @Query("sort") sort: String = "updated",
        @Query("direction") direction: String = "desc"
    ): List<RepositoryResponse>

    @GET("repos/{user}/{repo}/issues")
    suspend fun getIssues(
        @Path("user") user: String,
        @Path("repo") repo: String
    ): List<IssueResponse>
}
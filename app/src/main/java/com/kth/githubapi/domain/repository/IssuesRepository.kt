package com.kth.githubapi.domain.repository

import com.kth.githubapi.domain.model.Issue

interface IssuesRepository {
    suspend fun fetchIssues(user: String, repo: String): List<Issue>
}
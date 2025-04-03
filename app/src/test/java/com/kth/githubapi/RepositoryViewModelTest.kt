package com.kth.githubapi

import app.cash.turbine.test
import com.kth.githubapi.domain.model.Repository
import com.kth.githubapi.domain.usecase.GetRepositoriesUseCase
import com.kth.githubapi.presentation.RepositoriesViewModel
import com.kth.githubapi.presentation.state.RepositoriesUiState
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertIs

@OptIn(ExperimentalCoroutinesApi::class)
class RepositoriesViewModelTest {

    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    private val getRepositoriesUseCase: GetRepositoriesUseCase = mockk()
    private lateinit var viewModel: RepositoriesViewModel

    @Before
    fun setup() {
        viewModel = RepositoriesViewModel(getRepositoriesUseCase)
    }

    @Test
    fun `저장소 리스트가 있으면 Loaded 상태를 반환한다`() = runTest {
        // Given
        val fakeRepositories = listOf(
            Repository(
                id = 1,
                name = "CleanArchitecture",
                url = "https://github.com/test/clean",
                description = "Clean Architecture Repo",
                language = "Kotlin",
                starCount = 99
            )
        )
        coEvery {
            getRepositoriesUseCase("testUser")
        } returns flowOf(fakeRepositories)

        viewModel.uiState.test {
            // When
            viewModel.load("testUser")

            // Then
            skipItems(1)
            val state = awaitItem()
            assertIs<RepositoriesUiState.Loaded>(state)
        }
    }

    @Test
    fun `저장소가 비어 있으면 Empty 상태를 반환한다`() = runTest {
        // Given
        coEvery {
            getRepositoriesUseCase("testUser")
        } returns flowOf(emptyList())

        viewModel.uiState.test {
            // When
            viewModel.load("testUser")

            // Then
            skipItems(1)
            val state = awaitItem()
            assertIs<RepositoriesUiState.Empty>(state)
        }
    }

}
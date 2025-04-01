package com.kth.githubapi.presentation.screen

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.kth.githubapi.presentation.RepositoriesViewModel
import com.kth.githubapi.presentation.navigation.Route
import com.kth.githubapi.presentation.screen.preview.RepositoryListPreviewProvider
import com.kth.githubapi.presentation.state.RepositoriesUiState
import com.kth.githubapi.presentation.state.RepositoryUiItem

fun NavGraphBuilder.repositoriesRoute() {
    composable(
        route = Route.Repositories.route,
        arguments = listOf(
            navArgument("user") { type = NavType.StringType }
        ),
        deepLinks = listOf(
            navDeepLink { uriPattern = Route.Repositories.URI_PARAM_USER }
        )
    ) { backStackEntry ->
        val user = backStackEntry.arguments?.getString("user").orEmpty()
        RepositoriesScreen(user)
    }
}

@Composable
fun RepositoriesScreen(
    user: String,
    viewModel: RepositoriesViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(user) {
        viewModel.load(user)
        viewModel.errorFlow.collect { throwable ->
            Toast.makeText(context, "Error: ${throwable.message}", Toast.LENGTH_SHORT).show()
        }
    }

    when (val state = uiState) {
        is RepositoriesUiState.Loading -> LoadingScreen()
        is RepositoriesUiState.Loaded -> {
            RepositoriesList(
                items = state.items,
                onClick = { url ->
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                    context.startActivity(intent)
                }
            )
        }
    }
}

@Composable
fun RepositoriesList(
    items: List<RepositoryUiItem>,
    onClick: (String) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(items, key = { it.id }) { item ->
            RepositoryCard(item = item, onClick = onClick)
        }
    }
}

@Composable
fun RepositoryCard(
    item: RepositoryUiItem,
    onClick: (String) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxSize(),
        elevation = CardDefaults.cardElevation(4.dp),
        onClick = { onClick(item.url) }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = item.name,
                style = MaterialTheme.typography.titleLarge
            )

            item.description?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Edit, contentDescription = null, tint = Color.Blue)
                    Text(
                        text = item.language ?: "Unknown",
                        modifier = Modifier.padding(start = 4.dp),
                        style = MaterialTheme.typography.labelMedium
                    )
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Star, contentDescription = null, tint = Color(0xFFFFD700))
                    Text(
                        text = "${item.starCount}",
                        modifier = Modifier.padding(start = 4.dp),
                        style = MaterialTheme.typography.labelMedium
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RepositoriesListPreview(
    @PreviewParameter(RepositoryListPreviewProvider::class)
    items: List<RepositoryUiItem>
) {
    MaterialTheme {
        RepositoriesList(items = items, onClick = {})
    }
}
package com.kth.githubapi.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import coil.compose.rememberAsyncImagePainter
import com.kth.githubapi.presentation.IssuesViewModel
import com.kth.githubapi.presentation.navigation.Route
import com.kth.githubapi.presentation.state.IssueUiItem
import com.kth.githubapi.presentation.state.IssueUiItem.BannerItem
import com.kth.githubapi.presentation.state.IssueUiItem.IssueItem
import com.kth.githubapi.presentation.state.IssuesUiState
import com.kth.githubapi.ui.component.LoadingView

fun NavGraphBuilder.issuesRoute() {
    composable(
        route = Route.Issues.route,
        arguments = listOf(
            navArgument("user") { type = NavType.StringType },
            navArgument("repo") { type = NavType.StringType }
        ),
        deepLinks = listOf(
            navDeepLink { uriPattern = Route.Issues.URI_PATTERN_ISSUES },
            navDeepLink { uriPattern = Route.Issues.WEB_URI_PATTERN }
        )
    ) { backStackEntry ->
            val user = backStackEntry.arguments?.getString("user").orEmpty()
            val repo = backStackEntry.arguments?.getString("repo").orEmpty()
            IssuesScreen(user = user, repo = repo)
    }
}

@Composable
fun IssuesScreen(
   user: String,
   repo: String,
   viewModel: IssuesViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.loadIssues(user, repo)
    }

    when (val state = uiState) {
        is IssuesUiState.Loading -> LoadingView()
        is IssuesUiState.Loaded -> IssuesList(items = state.items)
    }
}

@Composable
fun IssuesList(items: List<IssueUiItem>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(9.dp)
    ) {
        items(items, key = {
            when (it) {
                is IssueItem -> "issue_${it.number}"
                is BannerItem -> "banner_${it.imageUrl}"
            }
        }) { item ->
            when (item) {
                is IssueItem -> IssueItemView(item)
                is BannerItem -> BannerImage(item)
            }
        }
    }
}

@Composable
fun IssueItemView(item: IssueItem) {
    Text(
        text = "#${item.number}: ${item.title}",
        style = MaterialTheme.typography.bodyLarge,
        modifier = Modifier.padding(vertical = 4.dp)
    )
}

@Composable
fun BannerImage(item: BannerItem) {
    Image(
        painter = rememberAsyncImagePainter(item.imageUrl),
        contentDescription = "배너",
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp)
            .padding(vertical = 8.dp),
        contentScale = ContentScale.Crop
    )
}

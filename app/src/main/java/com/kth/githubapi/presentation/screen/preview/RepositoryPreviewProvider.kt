package com.kth.githubapi.presentation.screen.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.kth.githubapi.presentation.state.RepositoryUiItem

class RepositoryListPreviewProvider : PreviewParameterProvider<List<RepositoryUiItem>> {
    override val values = sequenceOf(
        listOf(
            RepositoryUiItem(
                id = 1,
                name = "MVI-Pattern",
                url = "https://github.com/warrenth/MVI-Pattern",
                description = "A clean MVI implementation",
                language = "Kotlin",
                starCount = 120
            ),
            RepositoryUiItem(
                id = 2,
                name = "JetpackComposePlayground",
                url = "https://github.com/warrenth/ComposePlayground",
                description = "Try all Compose APIs",
                language = "Kotlin",
                starCount = 77
            ),
            RepositoryUiItem(
                id = 3,
                name = "NoDescriptionRepo",
                url = "https://github.com/warrenth/NoDesc",
                description = null,
                language = "Java",
                starCount = 0
            ),
            RepositoryUiItem(
                id = 4,
                name = "Super-Long-Repository-Name-For-Testing-Layout-Behavior-In-Preview",
                url = "https://github.com/warrenth/long",
                description = "This is a very long description meant to test how text wrapping behaves in preview rendering inside a Compose Card layout.",
                language = "Kotlin",
                starCount = 999
            ),
            RepositoryUiItem(
                id = 5,
                name = "多语言支持",
                url = "https://github.com/warrenth/multilang",
                description = "이 저장소는 여러 언어를 지원합니다。",
                language = "Swift",
                starCount = 88
            )
        )
    )
}
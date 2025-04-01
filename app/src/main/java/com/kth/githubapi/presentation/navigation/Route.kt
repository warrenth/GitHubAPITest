package com.kth.githubapi.presentation.navigation

sealed class Route(val route: String) {

    data object Splash : Route("splash")

    data object Repositories : Route("repositories/{user}") {
        // 동적 경로 생성
        fun create(user: String) = "repositories/$user"
        // 딥링크에서 이 결로를 URI로 인식할 수 있도록 패턴 지정
        const val URI_PARAM_USER = "repositories://{user}"
    }

    data object Issues : Route("issues/{user}/{repo}") {
        fun create(user: String, repo: String) = "issues/$user/$repo"
        const val URI_PATTERN_ISSUES = "issues://{user}/{repo}/issues"
        const val WEB_URI_PATTERN = "https://github.com/{user}/{repo}/issues"
    }
}
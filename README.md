# Jetpack Compose Navigation + Route + DeepLink 정리
Jetpack Compose 기반 **DeepLink**, **BackStack 제어**, **Route 관리**를 연습해보는 프로젝트입니다.

## Deeplink 로 들어 올 수 있는 경로 정리
앱 내부, 외부 앱, 웹브라우저, 푸시, Shortcut, 시스템


## Navigation 구성요소 (기본)

### NavHost 구성
```kotlin
NavHost(
    navController = navController,
    startDestination = Route.Splash.route
) {
    splashRoute(navController)
    repositoriesRoute()
}
```
- NavHost는 route를 관리하는 container
- 모든 화면은 composable()로 등록되어야 navigate가 가능

### NavGraphBuilder
```kotlin
fun NavGraphBuilder.repositoriesRoute() {
    composable(
        route = Route.Repositories.route,
        arguments = listOf(navArgument("user") { type = NavType.StringType }
        ),
        deepLinks = listOf(
            navDeepLink { uriPattern = Route.Repositories.URI_PARAM_USER }
        )
    ) { backStackEntry ->
        val user = backStackEntry.arguments?.getString("user").orEmpty()
        RepositoriesScreen(user)
    }
}
```
- Route 등록 및 DeepLink 설정
- arguments : user는 경로에 포함된 path parameter로, String 타입임을 명시, NavBackStackEntry로부터 안전하게 꺼내쓸 수 있음

### NavHostController
- `navigate(route)`로 화면 이동 및 `BackStack` 제어
- `popUpTo(...)` 등을 통해 백스택 흐름 제어 가능

### rememberNavController()
```kotlin
val navController = rememberNavController()
```
- Compose에서 recomposition 시 상태가 유지되도록 하기 위해 remember 사용
- 내부적으로 ViewModelStoreOwner, LifecycleOwner, SavedStateHandle과 연결
- Lifecycle-aware 하게 NavController를 안전하게 관리할 수 있음

## popUpTo로 BackStack 제어
```kotlin
navController.navigate(Route.Repositories.create("warrenth")) {
    popUpTo(Route.Splash.route) {
        inclusive = true
    }
}
```
- popUpTo(...)	backstack을 어디까지 제거할지 지정
- inclusive = true	해당 route까지 제거
- inclusive = false	해당 route는 유지

## Route 정의
```kotlin
sealed class Route(val route: String) {
    data object Splash : Route("splash")

    data object Repositories : Route("repositories/{user}") {
        fun create(user: String) = "repositories/$user"
        const val URI_PARAM_USER = "repositories://{user}"
    }
}
```


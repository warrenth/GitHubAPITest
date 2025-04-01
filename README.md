* NavHostController : backstack, navigate 등을 제어
* rememberNavController() : recomposition 중 navController 상태 유지
* NavGraphBuilder : navigation graph를 구성하는 DSL builder


* NavHost
- route 들을 실행 해주는 container
- composable로 화면 등록 
- navController.navigate("repositories/warrenth") 로 호출하면
  NavHost 에 등록된 composable(route = "...") 와 매칭
- 모든 composable이 route 에 등록 되어 있다면, 중앙 집중적 관리 가능
- Route sealed class 로 자동완성, 타입안전성, 실수 방지

navController.navigate("Route.Main.create("")") {
    popupTo("splash") { inclusive = true }
}
popUpTo(...): backstack을 어디까지 제거할지 결정
inclusive = true: 자신도 제거 → 뒤로가기 시 돌아가지 않음
inclusive = false: 자신은 유지 → 뒤로가기로 다시 볼 수 있음




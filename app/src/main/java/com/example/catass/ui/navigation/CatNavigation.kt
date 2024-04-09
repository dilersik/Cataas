package com.example.catass.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.catass.ui.views.CatsDetailView
import com.example.catass.ui.views.CatsView
import com.example.catass.ui.views.CatsViewModel

@Composable
fun CatNavigation() {
    val navController = rememberNavController()
    val catsViewModel: CatsViewModel = hiltViewModel()
    LaunchedEffect(key1 = true) {
        catsViewModel.loadCats()
    }
    NavHost(navController = navController, startDestination = CatNavigationEnum.HOME.name) {
        composable(CatNavigationEnum.HOME.name) {
            CatsView(navController, catsViewModel)
        }

        composable(
            CatNavigationEnum.DETAIL.name + "/{${CatNavigationEnum.DETAIL.param}}",
            arguments = listOf(navArgument(name = CatNavigationEnum.DETAIL.param) {
                type = NavType.StringType
            })
        ) {
            CatsDetailView(
                navController,
                catsViewModel,
                id = it.arguments?.getString(CatNavigationEnum.DETAIL.param)
            )
        }
    }
}
package com.myapp.appetisermovies.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.feature.movie_detail.presentation.composable.MovieDetailScreen
import com.feature.movie_list.presentation.composable.MovieListScreen

@Composable
fun MainNavigation(
    navController: NavHostController = rememberNavController()
) {

    NavHost(navController = navController, startDestination = Routes.MOVIE_LIST) {

        composable(Routes.MOVIE_LIST) {
            MovieListScreen(onItemClicked = { movieId ->
                navController.navigate(Routes.getMovieDetailRoute(movieId))
            })
        }

        composable(
            route = Routes.getMovieDetailRoute(),
            arguments = listOf(navArgument(Routes.MOVIE_DETAIL_ARG) {
                type = NavType.IntType
            })
        ) {
            val param = it.arguments?.getInt(Routes.MOVIE_DETAIL_ARG) ?: -1
            MovieDetailScreen(
                movieId = param,
                onGoBack = { navController.popBackStack()}
            )
        }
    }
}

object Routes {
    const val MOVIE_LIST = "movie_list"
    const val MOVIE_DETAIL = "movie_detail/"

    const val MOVIE_DETAIL_ARG = "movieId"

    fun getMovieDetailRoute(movieId: Int? = null): String {
        val argumnet = movieId ?: "{${MOVIE_DETAIL_ARG}}"
        return MOVIE_DETAIL + argumnet
    }
}
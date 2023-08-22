package com.example.presentation_common.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.presentation_common.R

private const val ROUTE_COMIC = "comic"
//private const val ARG_COMIC_ID = "comic_id"
private const val ROUTE_FAVORITE_COMICS = "favorite_comics"

sealed class NavRoutes {

    abstract val route: String
    abstract val titleId: Int

    object Comic : NavRoutes() {
        override val route = ROUTE_COMIC
        override val titleId: Int = R.string.comic_screen_title

/*
        val argument = ARG_COMIC_ID
        val routeWithArg = "$route/$argument"
        val arguments = listOf(
            navArgument(ARG_COMIC_ID) { type = NavType.LongType }
        )
*/
    }

    object FavoriteComics : NavRoutes() {
        override val route = ROUTE_FAVORITE_COMICS
        override val titleId: Int = R.string.favorite_comics_screen_title
    }
}
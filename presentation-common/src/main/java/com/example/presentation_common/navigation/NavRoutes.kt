package com.example.presentation_common.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.presentation_common.R

private const val ROUTE_COMIC = "comic"
private const val ROUTE_SETTINGS= "settings"
private const val ARG_COMIC_ID = "comic_id"
private const val ROUTE_FAVORITE_COMICS = "favorite_comics"

val allScreens = listOf(NavRoutes.Comic, NavRoutes.FavoriteComics, NavRoutes.ComicPreview, NavRoutes.Settings)

sealed class NavRoutes {

    abstract val route: String
    abstract val titleId: Int

    object Comic : NavRoutes() {
        override val route = ROUTE_COMIC
        override val titleId: Int = R.string.comic_screen_title
    }

    object FavoriteComics : NavRoutes() {
        override val route = ROUTE_FAVORITE_COMICS
        override val titleId: Int = R.string.favorite_comics_screen_title
    }

    object Settings : NavRoutes() {
        override val route: String = ROUTE_SETTINGS
        override val titleId: Int = R.string.settings_screen_title
    }

    object ComicPreview : NavRoutes() {
        val routeWithoutArg = ROUTE_COMIC
        val argument = ARG_COMIC_ID

        override val route: String = "$routeWithoutArg/{$argument}"
        override val titleId: Int = R.string.comic_preview_screen_title

        val arguments = listOf(navArgument(argument) { type = NavType.LongType })

        fun getRoute(comicId: Long) = "$routeWithoutArg/$comicId"
    }
}




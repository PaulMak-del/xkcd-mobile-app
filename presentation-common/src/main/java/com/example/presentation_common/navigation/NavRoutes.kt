package com.example.presentation_common.navigation

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.navArgument

private const val ROUTE_COMIC = "comic/%s"
private const val ARG_COMIC_ID = "comic/%s"
private const val ROUTE_FAVORITE_COMICS = "favorite_comics"

class NavRoutes {

    object Comic {
        val route = ROUTE_COMIC

        val argument = ARG_COMIC_ID
        val routeWithArg = "$route/$argument"
        val arguments = listOf(
            navArgument(ARG_COMIC_ID) { type = NavType.LongType }
        )
    }

    object FavoriteComics {
        val route = ROUTE_FAVORITE_COMICS
    }
}


@Composable
fun Smt() {
    Row {

    }
}
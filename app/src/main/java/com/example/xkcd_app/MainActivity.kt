package com.example.xkcd_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.presentation_comic.comic.ComicScreen
import com.example.presentation_common.navigation.NavRoutes
import com.example.xkcd_app.ui.theme.XKCD_APPTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            XKCD_APPTheme {
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    MyApp(navController)
                }
            }
        }
    }
}

@Composable
fun MyApp(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = NavRoutes.Comic.route
    ) {
        composable(
            route = NavRoutes.Comic.route,
            arguments = NavRoutes.Comic.arguments
        ) {
            ComicScreen(
                hiltViewModel()
            )
        }
        composable(
            route = NavRoutes.FavoriteComics.route,
        ) {
            //FavoriteComicsScreen()
        }
    }
}

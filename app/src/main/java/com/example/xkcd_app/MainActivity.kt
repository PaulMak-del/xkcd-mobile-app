package com.example.xkcd_app

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.presentation_comic.comic.ComicScreen
import com.example.presentation_comic.favorite_comics.FavoriteComicsScreen
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

                    MyApp(
                        applicationContext,
                        navController
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyApp(
    context: Context,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val screens = listOf(NavRoutes.Comic, NavRoutes.FavoriteComics)
    val currentBackStack by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStack?.destination
    val currentScreen = screens.find { it.route == currentDestination?.route } ?: NavRoutes.Comic

    Scaffold(
        topBar = {
            AppTabRow(
                context = context,
                screens = screens,
                screenIndex = screens.indexOf(currentScreen),
                currentScreen = currentScreen,
                onTabClick = { newScreen ->
                    navController.navigate(newScreen.route)
                }
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = NavRoutes.Comic.route,
            modifier = modifier
                .padding(innerPadding)
        ) {
            composable(
                route = NavRoutes.Comic.route,
            ) {
                ComicScreen(
                    hiltViewModel()
                )
            }
            composable(
                route = NavRoutes.FavoriteComics.route,
            ) {
                FavoriteComicsScreen(
                    hiltViewModel()
                )
            }
        }
    }
}

@Composable
fun AppTabRow(
    context: Context,
    screens: List<NavRoutes>,
    screenIndex: Int,
    currentScreen: NavRoutes,
    onTabClick: (NavRoutes) -> Unit = {}
) {
    TabRow(
        selectedTabIndex = screenIndex
    ) {
        screens.forEachIndexed { index, screen ->
            Tab(
                selected = (currentScreen == screen),
                onClick = { onTabClick(screen) },
                text = {
                    Text(
                        //text = screen.screenName,
                        text = context.getString(screen.titleId),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            )
        }
    }
}


















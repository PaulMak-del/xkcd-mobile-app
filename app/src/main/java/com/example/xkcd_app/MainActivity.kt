package com.example.xkcd_app

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
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
import com.example.presentation_comic.comic_preview.ComicShow
import com.example.presentation_comic.favorite_comics.FavoriteComicsScreen
import com.example.presentation_common.navigation.NavRoutes
import com.example.presentation_common.navigation.allScreens
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
                        this,
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
    val currentBackStack by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStack?.destination
    val currentScreen = allScreens.find { it.route == currentDestination?.route } ?: NavRoutes.Comic

    Scaffold(
        topBar = {
            when(currentScreen) {
                is NavRoutes.ComicPreview -> {
                    TopAppBar(
                        title = { Text(text = "Comic") },
                        navigationIcon = {
                            IconButton(
                                onClick = { navController.navigate(NavRoutes.FavoriteComics.route) }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.ArrowBack,
                                    contentDescription = null
                                )
                            }
                        }
                    )
                }
                else -> {
                    AppTabRow(
                        context = context,
                        screens = listOf(NavRoutes.Comic, NavRoutes.FavoriteComics),
                        screenIndex = allScreens.indexOf(currentScreen),
                        currentScreen = currentScreen,
                        onTabClick = { newScreen ->
                            navController.navigate(newScreen.route)
                        },
                    )
                }
            }
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
                    viewModel = hiltViewModel(),
                    context = context,
                )
            }
            composable(
                route = NavRoutes.FavoriteComics.route,
            ) {
                FavoriteComicsScreen(
                    viewModel = hiltViewModel(),
                    onComicClick = {
                        navController.navigate(NavRoutes.ComicPreview.getRoute(it))
                    }
                )
            }
            composable(
                route = NavRoutes.ComicPreview.route,
                arguments = NavRoutes.ComicPreview.arguments
            ) {
                val arg = it.arguments?.getLong("comic_id") ?: 1

                ComicShow(
                    viewModel = hiltViewModel(),
                    comicId = arg,
                    context = context,
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
    modifier: Modifier = Modifier,
    onTabClick: (NavRoutes) -> Unit = {}
) {
    TabRow(
        selectedTabIndex = screenIndex,
        modifier = modifier,
    ) {
        screens.forEachIndexed { index, screen ->
            Tab(
                selected = (currentScreen == screen),
                onClick = { onTabClick(screen) },
                text = {
                    Text(
                        text = context.getString(screen.titleId),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            )
        }
    }
}

















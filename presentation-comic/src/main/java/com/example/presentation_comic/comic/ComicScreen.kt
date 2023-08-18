package com.example.presentation_comic.comic

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.FirstPage
import androidx.compose.material.icons.filled.LastPage
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Shuffle
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import com.example.presentation_common.state.CommonScreen
import com.example.presentation_common.state.UIState

@Composable
fun ComicScreen(
    viewModel: ComicViewModel
) {
    var currentComicId by rememberSaveable {
        mutableStateOf( 1300L )
    }
    viewModel.loadComic(currentComicId)
    viewModel.comic.collectAsState().value.let { result ->
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.TopCenter)
            ) {
                TopButtons(
                    //isFavorite = if (result is UIState.Success) result.data.isFavorite else false,
                    onSearchClick = {
                        currentComicId = it
                    },
                    onFavoriteClick = {
                        if (result is UIState.Success) {
                            //result.data.isFavorite = !result.data.isFavorite
                            //viewModel.addComicToDB(result.data)
                        }
                    },
                    onSettingsClick = {},
                    onShareClick = {},
                )
                Spacer(
                    modifier = Modifier
                        .height(16.dp)
                )
                CommonScreen(result) { comicModel ->
                    Comic(
                        comicModel,
                        modifier = Modifier
                            .verticalScroll(rememberScrollState())
                    )
                }
            }
            ComicsNavigationButtons(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(16.dp),
                onFirstPageClick = {
                    currentComicId = 1
                },
                onChevronLeftClick = {
                    currentComicId--
                },
                onShuffleClick = {
                    currentComicId = (Math.random() * 2815 + 1).toLong()
                },
                onChevronRightClick = {
                    currentComicId++
                },
                onLastPageClick = {
                    currentComicId = 2816
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopButtons(
    //isFavorite: Boolean,
    modifier: Modifier = Modifier,
    onFavoriteClick: () -> Unit = {},
    onShareClick: () -> Unit = {},
    onSettingsClick: () -> Unit = {},
    onSearchClick: (Long) -> Unit = {},
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
    ) {
        var searchId by remember { mutableStateOf("") }
        val focusManager = LocalFocusManager.current

        TextField(
            value = searchId,
            onValueChange = {
                searchId = it.take(6)
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null
                )
            },
            label = { Text("Search panel") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    focusManager.clearFocus()

                    onSearchClick(searchId.toLong())
                }
            ),
            singleLine = true,
            modifier = Modifier
                .widthIn(128.dp)
                .heightIn(48.dp)
                .padding(8.dp)
        )
        Row {
            val mod = Modifier
                .padding(8.dp)
                .size(32.dp)

            IconButton(
                onClick = { onFavoriteClick() },
                modifier = mod
            ) {
                Icon(
                    imageVector = Icons.Default.FavoriteBorder,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onBackground,
                )
            }
            IconButton(
                onClick = { onShareClick() },
                modifier = mod
            ) {
                Icon(
                    imageVector = Icons.Default.Share,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onBackground,
                )
            }
            IconButton(
                onClick = { onSettingsClick() },
                modifier = mod
            ) {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onBackground,
                )
            }
        }
    }
}

@Composable
fun ComicsNavigationButtons(
    modifier: Modifier = Modifier,
    onFirstPageClick: () -> Unit = {},
    onChevronLeftClick: () -> Unit = {},
    onShuffleClick: () -> Unit = {},
    onChevronRightClick: () -> Unit = {},
    onLastPageClick: () -> Unit = {},
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = modifier
            .width(277.dp)
            .height(48.dp)
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(32.dp, 32.dp, 32.dp, 32.dp)
            )
    ) {
        val mod = Modifier
            .padding(8.dp)
            .size(32.dp)

        IconButton(
            onClick = { onFirstPageClick() }
        ) {
            Icon(
                imageVector = Icons.Default.FirstPage,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurface,
                modifier = mod
            )
        }
        IconButton(
            onClick = { onChevronLeftClick() }
        ) {
            Icon(
                imageVector = Icons.Default.ChevronLeft,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurface,
                modifier = mod
            )
        }
        IconButton(
            onClick = { onShuffleClick() }
        ) {
            Icon(
                imageVector = Icons.Default.Shuffle,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurface,
                modifier = mod
            )
        }
        IconButton(
            onClick = { onChevronRightClick() }
        ) {
            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurface,
                modifier = mod
            )
        }
        IconButton(
            onClick = { onLastPageClick() }
        ) {
            Icon(
                imageVector = Icons.Default.LastPage,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurface,
                modifier = mod
            )
        }
    }
}

@Composable
fun Comic(
    comicModel: ComicModel,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text(
            text = "${comicModel.id}# ${comicModel.title}",
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(
            modifier = Modifier
                .height(8.dp)
        )
        SubcomposeAsyncImage(
            model = comicModel.imageUrlPath,
            loading = {
                CircularProgressIndicator(modifier = Modifier
                    .size(64.dp)
                )
                      },
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth()
        )
        Spacer(
            modifier = Modifier
                .height(24.dp)
        )
        Text(
            text = comicModel.alt,
            textAlign = TextAlign.Center
        )
        Spacer(
            modifier = Modifier
                .height(64.dp)
        )
    }
}

@Preview
@Composable
fun ComicScreenPreview() {
        Surface(
            modifier = Modifier,
            color = MaterialTheme.colorScheme.background
        ) {
            Column {
                TopButtons()
                Comic(ComicModel(12, "title", "https://imgs.xkcd.com/comics/babies.png", "alt", false))
                ComicsNavigationButtons()
            }
        }
}

@Preview
@Composable
fun TopButtonsPreview() {
    Surface(
        modifier = Modifier,
        color = MaterialTheme.colorScheme.background
    ) {
        TopButtons()
    }
}

@Preview
@Composable
fun BottomButtonsPreview() {
    Surface(
        modifier = Modifier,
        color = MaterialTheme.colorScheme.background
    ) {
        ComicsNavigationButtons()
    }
}

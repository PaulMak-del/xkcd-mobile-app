package com.example.presentation_comic.comic

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
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
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.presentation_comic.R
import com.example.presentation_common.state.CommonScreen

@Composable
fun ComicScreen(
    viewModel: ComicViewModel
) {
    viewModel.loadComic(1300)
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
                TopButtons()
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
                    .padding(16.dp)
            )
        }
    }
}

@Composable
fun TopButtons(
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
    ) {
        TextField(
            value = "",
            onValueChange = {},
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null
                )
            },
            label = { Text("Search panel") },
            modifier = Modifier
                .widthIn(128.dp)
                .heightIn(48.dp)
                .padding(8.dp)
        )
        Image(
            imageVector = Icons.Default.FavoriteBorder,
            contentDescription = null,
            modifier = Modifier
                .padding(8.dp)
                .width(32.dp)
                .height(32.dp)
        )
        Image(
            imageVector = Icons.Default.Share,
            contentDescription = null,
            modifier = Modifier
                .padding(8.dp)
                .width(32.dp)
                .height(32.dp)
        )
        Image(
            imageVector = Icons.Default.Settings,
            contentDescription = null,
            modifier = Modifier
                .padding(8.dp)
                .width(32.dp)
                .height(32.dp)
        )
    }
}

@Composable
fun ComicsNavigationButtons(modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = modifier
            .paint(
                painter = painterResource(id = R.drawable.bottom_navigation_background),
                contentScale = ContentScale.FillBounds,
            )
    ) {
        Image(
            imageVector = Icons.Default.FirstPage,
            contentDescription = null,
            modifier = Modifier
                .width(32.dp)
                .height(32.dp)
        )
        Image(
            imageVector = Icons.Default.ChevronLeft,
            contentDescription = null,
            modifier = Modifier
                .width(32.dp)
                .height(32.dp)
        )
        Image(
            imageVector = Icons.Default.Shuffle,
            contentDescription = null,
            modifier = Modifier
                .width(32.dp)
                .height(32.dp)
        )
        Image(
            imageVector = Icons.Default.ChevronRight,
            contentDescription = null,
            modifier = Modifier
                .width(32.dp)
                .height(32.dp)
        )
        Image(
            imageVector = Icons.Default.LastPage,
            contentDescription = null,
            modifier = Modifier
                .width(32.dp)
                .height(32.dp)
        )
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
            text = comicModel.title
        )
        Spacer(
            modifier = Modifier
                .height(8.dp)
        )
        AsyncImage(
            model = comicModel.imageUrlPath,
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
            modifier = Modifier
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
                Comic(ComicModel(12, "title", "https://imgs.xkcd.com/comics/babies.png", "alt"))
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

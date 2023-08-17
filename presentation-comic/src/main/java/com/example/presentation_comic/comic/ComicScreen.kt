package com.example.presentation_comic.comic

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
//import coil.compose.AsyncImage
import com.example.presentation_common.state.CommonScreen

@Composable
fun ComicScreen(
    viewModel: ComicViewModel
) {
    Column {
        viewModel.loadComic(1531)
        TopButtons()
        viewModel.comic.collectAsState().value.let { result ->
            CommonScreen(result) { comicModel ->
                Comic(comicModel)
            }
        }
        ComicsNavigationButtons()
    }
}

@Composable
fun TopButtons(modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(8.dp)
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
                .wrapContentWidth()
                .heightIn(48.dp)
                .padding(8.dp)
        )
        Image(
            imageVector = Icons.Default.FavoriteBorder,
            contentDescription = null,
            modifier = Modifier
                .padding(8.dp)
        )
        Image(
            imageVector = Icons.Default.Share,
            contentDescription = null,
            modifier = Modifier
                .padding(8.dp)
        )
        Image(
            imageVector = Icons.Default.Settings,
            contentDescription = null,
            modifier = Modifier
                .padding(8.dp)
        )
    }
}

@Composable
fun ComicsNavigationButtons(modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(32.dp),
        //horizontalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        Image(imageVector = Icons.Default.FirstPage, contentDescription = null)
        Image(imageVector = Icons.Default.ChevronLeft, contentDescription = null)
        Image(imageVector = Icons.Default.Shuffle, contentDescription = null)
        Image(imageVector = Icons.Default.ChevronRight, contentDescription = null)
        Image(imageVector = Icons.Default.LastPage, contentDescription = null)
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
            .padding(16.dp)
    ) {
        Text(
            text = comicModel.title
        )
        /*
        AsyncImage(
            model = comicModel.imageUrlPath,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
        )

         */
        Text(
            text = comicModel.alt
        )
    }
}

@Preview
@Composable
fun ComicScreePreview() {
        Surface(
            modifier = Modifier,
            color = MaterialTheme.colorScheme.background
        ) {
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
fun ComicPreview() {
    Surface(
        modifier = Modifier,
        color = MaterialTheme.colorScheme.background
    ) {
    }
}

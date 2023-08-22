package com.example.presentation_comic.favorite_comics

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.example.presentation_common.state.CommonScreen

@Composable
fun FavoriteComicsScreen(
    viewModel: FavoriteComicsViewModel,
    modifier : Modifier = Modifier
) {
    viewModel.loadFavoriteComicsList()
    viewModel.favoriteComicsList.collectAsState().value.let { result ->
        CommonScreen(result) {  favoriteComicsList ->
            FavoriteComicsGrid(favoriteComicsList)
        }
    }
}

@Composable
fun FavoriteComicsGrid(
    favoriteComicsList: List<ComicPreviewModel>,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(135.dp),
        modifier = modifier
    ) {
        items(favoriteComicsList) { comicPreviewModel ->
            ComicPreview(
                comic = comicPreviewModel,
                modifier = Modifier
                    .padding(16.dp)
            )
        }
    }
}

@Composable
fun ComicPreview(
    comic: ComicPreviewModel,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .width(216.dp)
            .wrapContentHeight()
            .clip(RoundedCornerShape(11.dp))
            .border(
                BorderStroke(1.dp, MaterialTheme.colorScheme.onBackground),
                shape = RoundedCornerShape(11.dp)
            )
            .background(MaterialTheme.colorScheme.surface)
    ) {
        SubcomposeAsyncImage(
            model = comic.imageUrlPath,
            loading = {
                CircularProgressIndicator(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp)
                )
                      },
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .height(64.dp)
        )
        Text(
            text = comic.title,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier
                .padding(PaddingValues(
                    top = 8.dp,
                    start = 8.dp,
                    end = 8.dp
                ))
        )
        Text(
            text = "Number: ${comic.id}",
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier
                .padding(PaddingValues(
                    bottom = 8.dp,
                    start = 8.dp,
                    end = 8.dp
                ))
        )
    }
}


@Preview(showBackground = true)
@Composable
fun ComicPreviewPreview() {
    ComicPreview(
        ComicPreviewModel(
            id = 1300,
            title = "test title",
            imageUrlPath = "https://imgs.xkcd.com/comics/the_bdlpswdks_effect.png"
        )
    )
}

@Preview(showBackground = true)
@Composable
fun FavoriteComicsGridPreview() {
    FavoriteComicsGrid(listOf(
        ComicPreviewModel(id = 1, title = "hello", imageUrlPath = ""),
        ComicPreviewModel(id = 2, title = "world", imageUrlPath = ""),
        ComicPreviewModel(id = 3, title = "bye", imageUrlPath = ""),
        ComicPreviewModel(id = 4, title = "war", imageUrlPath = ""),
        ComicPreviewModel(id = 5, title = "turtle", imageUrlPath = ""),
    ))
}

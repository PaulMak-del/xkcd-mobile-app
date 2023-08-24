package com.example.presentation_comic.comic_preview

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import com.example.presentation_comic.comic.ComicModel
import com.example.presentation_common.state.UIState


@Composable
fun ComicShow(
    viewModel: ComicShowViewModel,
    comicId: Long,
    context: Context,
) {
    viewModel.loadComic(comicId)
    viewModel.comic.collectAsState().value.let { result ->
        when (result) {
            is UIState.Loading -> ComicLoadingScreen()
            is UIState.Error -> ComicErrorScreen()
            is UIState.Success -> ComicShowScreen(
                comicModel = result.data,
                onFavoriteClick = {
                    if (result.data.isFavorite) {
                        viewModel.removeComicFromFavorite(result.data)
                    } else {
                        viewModel.addComicToFavorite(result.data)
                    }
                },
                onShareClick = {
                    viewModel.shareComicByUrl(context, result.data.imageUrlPath)
                },
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
            )
        }
    }
}

@Composable
fun ComicLoadingScreen() {

}

@Composable
fun ComicErrorScreen() {

}

@Composable
fun ComicShowScreen(
    comicModel: ComicModel,
    modifier: Modifier = Modifier,
    onFavoriteClick: () -> Unit = {},
    onShareClick: () -> Unit = {},
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
                CircularProgressIndicator(
                    modifier = Modifier
                        .padding(8.dp)
                        .wrapContentSize()
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
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            IconButton(
                onClick = { onFavoriteClick() },
            ) {
                if (comicModel.isFavorite) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onBackground,
                    )
                } else {
                    Icon(
                        imageVector = Icons.Default.FavoriteBorder,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onBackground,
                    )
                }
            }
            IconButton(
                onClick = { onShareClick() },
            ) {
                Icon(
                    imageVector = Icons.Default.Share,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onBackground,
                )
            }
        }
    }
}

@Preview
@Composable
fun ScreenPreview() {
    Surface() {
        ComicShowScreen(comicModel = ComicModel(1, "", "", "", true))
    }
}




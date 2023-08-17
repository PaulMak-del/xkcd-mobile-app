package com.example.presentation_common.state

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Snackbar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment

@Composable
fun<T: Any> CommonScreen(
    state: UIState<T>,
    onSuccess: @Composable (T) -> Unit
) {
    when (state) {
        is UIState.Loading -> Loading()
        is UIState.Error -> Error(errorMessage = state.errorMessage)
        is UIState.Success -> onSuccess(state.data)
    }
}

@Composable
fun Error(
    errorMessage: String,
    modifier : Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Bottom
    ) {
        Snackbar {
            Text(text = errorMessage)
        }
    }
}

@Composable
fun Loading(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator()
    }
}


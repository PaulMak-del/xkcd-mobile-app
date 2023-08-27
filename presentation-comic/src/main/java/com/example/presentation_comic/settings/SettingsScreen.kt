package com.example.presentation_comic.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SettingsScreen(
    //viewModel: SettingsViewModel,
    themeState: Int,
    modifier: Modifier = Modifier,
    onRadioButtonClick: (themeId: Int) -> Unit = {},
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = "App Theme",
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.outline,
            modifier = modifier
                .padding(PaddingValues(horizontal = 16.dp))
        )
        Surface {
            Column(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.primaryContainer)
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(PaddingValues(horizontal = 16.dp))
                ) {
                    Text(
                        text = "Light",
                    )
                    RadioButton(selected = themeState == 0, onClick = { onRadioButtonClick(0) })
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(PaddingValues(horizontal = 16.dp))
                ) {
                    Text(
                        text = "Dark",
                    )
                    RadioButton(selected = themeState == 1, onClick = { onRadioButtonClick(1) })
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(PaddingValues(horizontal = 16.dp))
                ) {
                    Text(text = "System")
                    RadioButton(selected = themeState == 2, onClick = { onRadioButtonClick(2) })
                }
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 320)
@Composable
fun SettingsScreenPreview() {
    Surface {
        SettingsScreen(1)
    }
}

package com.gazim.myvocabluary.app.component

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gazim.myvocabluary.app.theme.MyVocabluaryTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TestWordsComponent(word: String = "", yes: () -> Unit = {}, no: () -> Unit = {}, back: () -> Unit = {}) {
    Surface {
        Scaffold(
            topBar = {
                TopAppBar(title = { Text("Test") }, navigationIcon = {
                    IconButton(onClick = back) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                })
            },
        ) { paddingValue ->
            val contentPadding = with(paddingValue) {
                PaddingValues(
                    top = 16.dp + calculateTopPadding(),
                    bottom = 16.dp + calculateBottomPadding(),
                    start = 16.dp,
                    end = 16.dp,
                )
            }
            Column(modifier = Modifier.padding(contentPadding)) {
                Text(word)
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun TestWordsComponentPreview() {
    TestWordsComponent("Word")
}

@Preview(showSystemUi = true)
@Composable
fun TestWordsComponentPreviewWithTheme() {
    MyVocabluaryTheme {
        TestWordsComponentPreview()
    }
}

@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun TestWordsComponentPreviewWithDarkTheme() {
    TestWordsComponentPreviewWithTheme()
}
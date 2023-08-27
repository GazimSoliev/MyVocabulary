package com.gazim.myvocabluary.app.component

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gazim.myvocabluary.app.model.VocabularyWord
import com.gazim.myvocabluary.app.theme.MyVocabluaryTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListOfWordsComponent(words: List<VocabularyWord> = listOf(), addWord: () -> Unit = {}, launchTest: () -> Unit = {}) {
    Surface {
        Scaffold(
            topBar = {
                TopAppBar(title = {
                    Text("Words")
                })
            },
            floatingActionButton = {
                Column {
                    FloatingActionButton(onClick = launchTest,
                        containerColor = MaterialTheme.colorScheme.primary, contentColor = MaterialTheme.colorScheme.onPrimary) {
                        Icon(Icons.Default.PlayArrow, contentDescription = "Test words")
                    }
                    Spacer(Modifier.height(16.dp))
                    FloatingActionButton(onClick = addWord) {
                        Icon(Icons.Default.Add, contentDescription = "Add a word")
                    }
                }
            }
        ) { paddingValue ->
            val contentPadding = with(paddingValue) {
                PaddingValues(
                    top = 16.dp + calculateTopPadding(),
                    bottom = 16.dp + calculateBottomPadding(),
                    start = 16.dp,
                    end = 16.dp
                )
            }
            LazyColumn(contentPadding = contentPadding, verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(words) {
                    Card(Modifier.fillMaxWidth()) {
                        Text("${it.word} - [${it.transcription}] - ${it.translation}", modifier = Modifier.padding(16.dp), maxLines = 1, softWrap = false, overflow = TextOverflow.Ellipsis)
                    }
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun ListOfWordsComponentPreview() {
    ListOfWordsComponent(words = List(20) {
        VocabularyWord(word = "word", transcription = "transcription", translation = "translation", linksOfPronunciation = listOf("link"))
    })
}

@Preview(showSystemUi = true)
@Composable
fun ListOfWordsComponentPreviewWithTheme() {
    MyVocabluaryTheme {
        ListOfWordsComponentPreview()
    }
}

@Preview(showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun DarkListOfWordsComponentPreviewWithTheme() {
    ListOfWordsComponentPreviewWithTheme()
}
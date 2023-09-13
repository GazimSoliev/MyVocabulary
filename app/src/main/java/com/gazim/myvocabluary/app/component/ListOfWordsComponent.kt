package com.gazim.myvocabluary.app.component

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Publish
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gazim.myvocabluary.app.model.WordID
import com.gazim.myvocabluary.app.theme.MyVocabluaryTheme

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun ListOfWordsComponent(
    words: List<WordID> = emptyList(),
    addWord: () -> Unit = {},
    launchTest: () -> Unit = {},
    onWordClick: (Int) -> Unit = {},
    toImportScreen: () -> Unit = {},
    onDelete: (List<WordID>) -> Unit = {}
) {
    val haptics = LocalHapticFeedback.current
    val chosenWords = remember { mutableStateListOf<WordID>() }
    val choseMode by remember { derivedStateOf { chosenWords.isNotEmpty() } }
    Surface {
        Scaffold(
            topBar = {
                if (choseMode)
                    CenterAlignedTopAppBar(
                        title = {
                            Text("Selected words: ${chosenWords.size}")
                        },
                        navigationIcon = {
                            IconButton(onClick = { chosenWords.clear() }) {
                                Icon(Icons.Default.Cancel, contentDescription = "Import")
                            }
                        },
                        actions = {
                            IconButton(onClick = { onDelete(chosenWords.toList()); chosenWords.clear() }) {
                                Icon(Icons.Default.Delete, contentDescription = "Import")
                            }
                        },
                    )
                else
                    TopAppBar(
                        title = {
                            Text("Words")
                        },
                        actions = {
                            IconButton(onClick = toImportScreen) {
                                Icon(Icons.Default.Publish, contentDescription = "Import")
                            }
                        },
                    )
            },
            floatingActionButton = {
                Column {
                    FloatingActionButton(
                        onClick = launchTest,
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary,
                    ) {
                        Icon(Icons.Default.PlayArrow, contentDescription = "Test words")
                    }
                    Spacer(Modifier.height(16.dp))
                    FloatingActionButton(onClick = addWord) {
                        Icon(Icons.Default.Add, contentDescription = "Add a word")
                    }
                }
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
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = contentPadding,
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                items(words, key = WordID::id) {
                    Card(
                        Modifier
                            .fillMaxWidth()
                            .combinedClickable(
                                onLongClick = {
                                    if (choseMode) return@combinedClickable
                                    haptics.performHapticFeedback(HapticFeedbackType.LongPress)
                                    chosenWords.add(it)
                                },
                                onClick = {
                                    when {
                                        chosenWords.contains(it) -> chosenWords.remove(it)
                                        choseMode -> chosenWords.add(it)
                                        else -> onWordClick(it.id)
                                    }
                                }).animateItemPlacement(),
                        border = if (chosenWords.contains(it)) BorderStroke(
                            1.dp,
                            MaterialTheme.colorScheme.outline
                        ) else null
                    ) {
                        Column(
                            Modifier.padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                        ) {
                            Text(
                                "${it.word} - [${it.transcription}] - ${it.translation}",
                                maxLines = 1,
                                softWrap = false,
                                overflow = TextOverflow.Ellipsis,
                            )
                            Text("${it.createdAt}")
                        }
                    }
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun ListOfWordsComponentPreview() {
    ListOfWordsComponent(
        words = List(20) {
            WordID(word = "word", transcription = "transcription", translation = "translation")
        },
    )
}

@Preview(showSystemUi = true)
@Composable
fun ListOfWordsComponentPreviewWithTheme() {
    MyVocabluaryTheme {
        ListOfWordsComponentPreview()
    }
}

@Preview(
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
fun DarkListOfWordsComponentPreviewWithTheme() {
    ListOfWordsComponentPreviewWithTheme()
}

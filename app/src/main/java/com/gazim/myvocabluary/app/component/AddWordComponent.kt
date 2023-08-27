package com.gazim.myvocabluary.app.component

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gazim.myvocabluary.app.theme.MyVocabluaryTheme

private typealias TextFieldChange = (TextFieldValue) -> Unit

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddWordComponent(
    word: TextFieldValue = TextFieldValue(),
    transcription: TextFieldValue = TextFieldValue(),
    translation: TextFieldValue = TextFieldValue(),
    changeWord: TextFieldChange = {},
    changeTranscription: TextFieldChange = {},
    changeTranslation: TextFieldChange = {},
    linksOfPronunciation: List<Pair<TextFieldValue, TextFieldChange>> = listOf(),
    addTextField: () -> Unit = {},
    back: () -> Unit = {},
) {
    Surface {
        Scaffold(
            topBar = {
                TopAppBar(title = { Text("Add a word") }, navigationIcon = {
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
            LazyColumn(
                contentPadding = contentPadding,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                item {
                    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = word,
                            onValueChange = changeWord,
                            label = { Text("Word") },
                        )
                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = transcription,
                            onValueChange = changeTranscription,
                            label = { Text("Transcription") },
                        )
                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = translation,
                            onValueChange = changeTranslation,
                            label = { Text("Translation") },
                        )
                    }
                }
                itemsIndexed(linksOfPronunciation) { i, it ->
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = it.first,
                        onValueChange = it.second,
                        label = { Text("Link №${i + 1}") },
                    )
                }
                item {
                    FilledTonalIconButton(onClick = addTextField) {
                        Icon(
                            Icons.Default.Add,
                            contentDescription = "Add a new text field for link",
                        )
                    }
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun AddWordComponentPreview() {
    AddWordComponent(
        linksOfPronunciation = listOf(
            TextFieldValue() to {},
            TextFieldValue() to {},
        ),
    )
}

@Preview(showSystemUi = true)
@Composable
fun AddWordComponentPreviewWithTheme() {
    MyVocabluaryTheme {
        AddWordComponentPreview()
    }
}

@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun AddWordComponentPreviewWithDarkTheme() {
    AddWordComponentPreviewWithTheme()
}

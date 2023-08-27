package com.gazim.myvocabluary.app.component

import android.content.res.Configuration
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gazim.myvocabluary.app.theme.MyVocabluaryTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LookWordComponent(
    word: String = "",
    transcription: String = "",
    translation: String = "",
    linksOfPronunciation: List<String> = listOf(),
    back: () -> Unit = {}
) {
    @Composable
    fun StyledWord(title: String, word: String) {
        Column {
            Text(title, modifier = Modifier.padding(start = 16.dp), style = MaterialTheme.typography.labelLarge)
            Spacer(Modifier.padding(4.dp))
            Card {
                Text(
                    word,
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    style = MaterialTheme.typography.displaySmall
                )
            }
        }
    }

    @Composable
    fun LinkStyledWord(title: String, word: String) {
        var expanded by remember { mutableStateOf(false) }
        var showExpand by remember { mutableStateOf(false) }
        Column {
            Text(title, modifier = Modifier.padding(start = 16.dp), style = MaterialTheme.typography.labelLarge)
            Spacer(Modifier.padding(4.dp))
            Card(Modifier.animateContentSize()) {
                Box(
                    modifier = Modifier.heightIn(min = 72.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Row(Modifier.padding(16.dp)) {
                        Text(
                            text = word,
                            modifier = Modifier
                                .weight(1f)
                                .align(Alignment.CenterVertically)
                                .animateContentSize(),
                            maxLines = if (expanded) Int.MAX_VALUE else 1,
                            overflow = TextOverflow.Ellipsis,
                            style = MaterialTheme.typography.headlineSmall,
                            onTextLayout = {
                                if (it.hasVisualOverflow && !showExpand) showExpand = true
                            }
                        )
                        if (showExpand) IconButton(onClick = { expanded = !expanded }) {
                            Icon(
                                if (expanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                                contentDescription = "Expand"
                            )
                        }
                    }
                }
            }
        }
    }
    Surface {
        Scaffold(
            topBar = {
                TopAppBar(title = { Text("Look a word") }, navigationIcon = {
                    IconButton(onClick = back) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                })
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
            LazyColumn(
                contentPadding = contentPadding,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(32.dp)
            ) {
                item {
                    Column(verticalArrangement = Arrangement.spacedBy(32.dp)) {
                        StyledWord("Word", word)
                        StyledWord("Transcription", transcription)
                        StyledWord("Translation", translation)
                    }
                }
                itemsIndexed(linksOfPronunciation) { i, it ->
                    LinkStyledWord("Link №${i + 1}", it)
                }
            }
        }
    }

}

@Preview(showSystemUi = true)
@Composable
fun LookWordComponentPreview() {
    LookWordComponent(
        "Hello",
        linksOfPronunciation = listOf(
            "https://www.google.com/search?q=install+cronie+ubunte&oq=install+cronie+ubunte&aqs=chrome..69i57.6889j0j1&sourceid=chrome&ie=UTF-8",
            "link2"
        )
    )
}

@Preview(showSystemUi = true)
@Composable
fun LookWordComponentPreviewWithTheme() {
    MyVocabluaryTheme {
        LookWordComponentPreview()
    }
}

@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun LookWordComponentPreviewWithDarkTheme() {
    LookWordComponentPreviewWithTheme()
}


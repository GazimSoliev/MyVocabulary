package com.gazim.myvocabluary.app.component

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gazim.myvocabluary.app.theme.MyVocabluaryTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TestWordsComponent(
    word: String = "",
    transcription: String = "",
    translation: String = "",
    yes: () -> Unit = {},
    no: () -> Unit = {},
    back: () -> Unit = {},
    transcriptionHidden: Boolean = true,
    translationHidden: Boolean = true,
    onTranscriptionClick: () -> Unit = {},
    onTranslationClick: () -> Unit = {},
) {
    @Composable
    fun HiddenWord(
        buttonTitle: String,
        hiddenWord: String,
        hidden: Boolean,
        onClick: () -> Unit = {}
    ) {
        Box(
            Modifier
                .width(IntrinsicSize.Max)
                .height(IntrinsicSize.Max),
            contentAlignment = Alignment.Center
        ) {
            Text(
                hiddenWord,
                style = MaterialTheme.typography.displaySmall,
                modifier = Modifier.clickable(onClick = onClick)
            )
            AnimatedVisibility(hidden) {
                Box(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.background)
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    TextButton(onClick = onClick) {
                        Icon(Icons.Default.Visibility, contentDescription = "Show word")
                        Spacer(Modifier.width(8.dp))
                        Text(buttonTitle)
                    }
                }
            }
        }
    }
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
            Column(
                modifier = Modifier.padding(contentPadding),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(word, style = MaterialTheme.typography.displayLarge)
                Spacer(Modifier.height(32.dp))
                HiddenWord(buttonTitle = "Show transcription", hiddenWord = transcription, hidden = transcriptionHidden, onClick = onTranscriptionClick)
                Spacer(Modifier.height(32.dp))
                HiddenWord("Show translation", hiddenWord = translation, hidden = translationHidden, onClick = onTranslationClick)
                Spacer(Modifier.weight(1f))
                Text("Do you remember this word?")
                Spacer(Modifier.height(32.dp))
                Row {
                    Spacer(Modifier.weight(1f))
                    Button(onClick = yes) {
                        Text("Yes")
                    }
                    Spacer(Modifier.weight(1f))
                    Button(onClick = no) {
                        Text("No")
                    }
                    Spacer(Modifier.weight(1f))
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun TestWordsComponentPreview() {
    TestWordsComponent("Word", transcription = "transcription", translation = "translation", transcriptionHidden = false)
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
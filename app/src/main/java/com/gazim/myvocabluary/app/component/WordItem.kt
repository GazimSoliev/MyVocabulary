package com.gazim.myvocabluary.app.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gazim.myvocabluary.app.theme.MyVocabluaryTheme

@Composable
fun WordItem(
    modifier: Modifier = Modifier,
    selected: Boolean = false,
    word: String = "",
    transcription: String = "",
    translation: String = "",
    createdAt: String = "",
) {
    Card(
        modifier = modifier,
        border = if (selected) {
            BorderStroke(
                4.dp,
                colorScheme.primary,
            )
        } else {
            null
        },
    ) {
        Column(
            Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Text(
                "$word - [$transcription] - $translation",
                maxLines = 1,
                softWrap = false,
                overflow = TextOverflow.Ellipsis,
            )
            Text(createdAt)
        }
    }
}

@Preview
@Composable
fun WordItemPreview() {
    WordItem(
        word = "word",
        transcription = "transcription",
        translation = "translation",
        createdAt = "01/09/2023 12:00",
    )
}

@Preview
@Composable
fun WordItemPreviewWithTheme() {
    MyVocabluaryTheme {
        WordItemPreview()
    }
}

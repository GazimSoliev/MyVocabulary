package com.gazim.myvocabluary.app.feature.import_words

import androidx.compose.ui.text.input.TextFieldValue
import com.gazim.myvocabluary.app.common.IState
import com.gazim.myvocabluary.extensions.now
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime

data class ImportWordsState(
    val localDate: LocalDate = LocalDate.now(),
    val localTime: LocalTime = LocalTime.now(),
    val textForImport: TextFieldValue = TextFieldValue(),
): IState
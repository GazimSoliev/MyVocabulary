package com.gazim.myvocabluary.app.feature.import_words

import androidx.compose.ui.text.input.TextFieldValue
import com.gazim.myvocabluary.app.common.IAction
import com.gazim.myvocabluary.app.common.ISideEffect
import com.gazim.myvocabluary.app.common.IState
import com.gazim.myvocabluary.extensions.now
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime

data class ImportWordsState(
    val localDate: LocalDate = LocalDate.now(),
    val localTime: LocalTime = LocalTime.now(),
    val textForImport: TextFieldValue = TextFieldValue(),
) : IState

sealed interface ImportWordsSideEffect : ISideEffect {
    data object Back : ImportWordsSideEffect
}

sealed interface ImportWordsAction : IAction {
    data class ChangeDate(val localDate: LocalDate) : ImportWordsAction
    data class ChangeTime(val localTime: LocalTime) : ImportWordsAction
    data class ChangeText(val text: TextFieldValue) : ImportWordsAction
    data object Import : ImportWordsAction
    data object Back : ImportWordsAction
}

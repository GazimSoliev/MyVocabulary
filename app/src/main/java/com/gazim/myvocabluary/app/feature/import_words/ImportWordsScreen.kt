package com.gazim.myvocabluary.app.feature.import_words

import androidx.compose.runtime.Composable
import com.gazim.myvocabluary.app.common.BaseScreen
import com.gazim.myvocabluary.app.component.ImportScreenComponent
import com.gazim.myvocabluary.app.feature.import_words.ImportWordsAction.Back
import com.gazim.myvocabluary.app.feature.import_words.ImportWordsAction.ChangeDate
import com.gazim.myvocabluary.app.feature.import_words.ImportWordsAction.ChangeText
import com.gazim.myvocabluary.app.feature.import_words.ImportWordsAction.ChangeTime
import com.gazim.myvocabluary.app.feature.import_words.ImportWordsAction.Import

class ImportWordsScreen :
    BaseScreen<ImportWordsState, ImportWordsSideEffect, ImportWordsAction, ImportWordsViewModel>(ImportWordsViewModel::class) {
    override suspend fun handleSideEffect(sideEffect: ImportWordsSideEffect) {
        when (sideEffect) {
            ImportWordsSideEffect.Back -> navigator.pop()
        }
    }

    @Composable
    override fun Screen() {
        ImportScreenComponent(
            onDateChange = { sendAction(ChangeDate(it)) },
            onTimeChange = { sendAction(ChangeTime(it)) },
            onTextChange = { sendAction(ChangeText(it)) },
            back = { sendAction(Back) },
            import = { sendAction(Import) },
            textForImport = state.textForImport,
        )
    }
}

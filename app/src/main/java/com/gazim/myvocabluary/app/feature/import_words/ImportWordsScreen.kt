package com.gazim.myvocabluary.app.feature.import_words

import androidx.compose.runtime.Composable
import com.bumble.appyx.navigation.modality.BuildContext
import com.gazim.myvocabluary.app.common.BaseScreen
import com.gazim.myvocabluary.app.component.ImportScreenComponent
import com.gazim.myvocabluary.app.feature.import_words.ImportWordsAction.Back
import com.gazim.myvocabluary.app.feature.import_words.ImportWordsAction.ChangeDate
import com.gazim.myvocabluary.app.feature.import_words.ImportWordsAction.ChangeText
import com.gazim.myvocabluary.app.feature.import_words.ImportWordsAction.ChangeTime
import com.gazim.myvocabluary.app.feature.import_words.ImportWordsAction.Import

class ImportWordsScreen(buildContext: BuildContext, private val backAction: () -> Unit) :
    BaseScreen<ImportWordsState, ImportWordsSideEffect, ImportWordsAction, ImportWordsViewModel>(
        buildContext,
        ImportWordsViewModel::class
    ) {
    override suspend fun handleSideEffect(sideEffect: ImportWordsSideEffect) {
        when (sideEffect) {
            ImportWordsSideEffect.Back -> backAction()
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
package com.gazim.myvocabluary.app.feature.import_words

import com.gazim.myvocabluary.app.common.BaseViewModel
import com.gazim.myvocabluary.app.feature.import_words.ImportWordsAction.Back
import com.gazim.myvocabluary.app.feature.import_words.ImportWordsAction.ChangeDate
import com.gazim.myvocabluary.app.feature.import_words.ImportWordsAction.ChangeText
import com.gazim.myvocabluary.app.feature.import_words.ImportWordsAction.ChangeTime
import com.gazim.myvocabluary.app.feature.import_words.ImportWordsAction.Import
import com.gazim.myvocabluary.data.DatabaseRepository
import com.gazim.myvocabluary.data.parser.WordsParser
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class ImportWordsViewModel(
    private val databaseRepository: DatabaseRepository,
    private val wordsParser: WordsParser
) : BaseViewModel<ImportWordsState, ImportWordsSideEffect, ImportWordsAction>() {
    override fun handleAction(action: ImportWordsAction) {
        intent {
            when (action) {
                is ChangeDate -> reduce { state.copy(localDate = action.localDate) }
                is ChangeTime -> reduce { state.copy(localTime = action.localTime) }
                is ChangeText -> reduce { state.copy(textForImport = action.text) }
                is Back -> postSideEffect(ImportWordsSideEffect.Back)
                is Import -> {
                    databaseRepository.insertWords(wordsParser.parse(state.textForImport.text))
                    postSideEffect(ImportWordsSideEffect.Back)
                }
            }
        }
    }

    override val container: Container<ImportWordsState, ImportWordsSideEffect> =
        container(ImportWordsState())
}
package com.gazim.myvocabluary.app.feature.word_add

import androidx.compose.ui.text.input.TextFieldValue
import com.gazim.myvocabluary.app.common.BaseViewModel
import com.gazim.myvocabluary.app.feature.word_add.WordAddAction.AddLink
import com.gazim.myvocabluary.app.feature.word_add.WordAddAction.EditLink
import com.gazim.myvocabluary.app.feature.word_add.WordAddAction.EditTranscription
import com.gazim.myvocabluary.app.feature.word_add.WordAddAction.EditTranslation
import com.gazim.myvocabluary.app.feature.word_add.WordAddAction.EditWord
import com.gazim.myvocabluary.app.feature.word_add.WordAddAction.Save
import com.gazim.myvocabluary.app.feature.word_add.WordAddSideEffect.Back
import com.gazim.myvocabluary.app.model.Link
import com.gazim.myvocabluary.app.model.Word
import com.gazim.myvocabluary.data.DatabaseRepository
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class WordAddViewModel(private val databaseRepository: DatabaseRepository) :
    BaseViewModel<WordAddState, WordAddSideEffect, WordAddAction>() {
    override fun handleAction(action: WordAddAction) {
        intent {
            when (action) {
                is AddLink -> reduce { state.copy(links = state.links.plus(TextFieldValue())) }
                is EditLink -> reduce { state.copy(links = state.links.mapIndexed { i, value -> if (i == action.index) action.link else value }) }
                is EditTranscription -> reduce { state.copy(transcription = action.transcription) }
                is EditTranslation -> reduce { state.copy(translation = action.translation) }
                is EditWord -> reduce { state.copy(word = action.word) }
                is Save -> {
                    val word = databaseRepository.insertWord(
                        Word(
                            word = state.word.text,
                            transcription = state.transcription.text,
                            translation = state.translation.text,
                        ),
                    )
                    databaseRepository.insertLinks(
                        state.links.map {
                            Link(
                                link = it.text,
                                wordId = word.id,
                            )
                        },
                    )
                    postSideEffect(Back)
                }
                is WordAddAction.Back -> {
                    postSideEffect(Back)
                    this@WordAddViewModel.onCleared()
                }
            }
        }
    }

    override val container: Container<WordAddState, WordAddSideEffect> = container(WordAddState())
}

package com.gazim.myvocabluary.app.feature.word_view

import com.gazim.myvocabluary.app.common.BaseViewModel
import com.gazim.myvocabluary.app.feature.word_view.WordViewAction.Back
import com.gazim.myvocabluary.data.IChosenWord
import com.gazim.myvocabluary.data.IDatabaseRepository
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class WordViewViewModel(
    private val chosenWord: IChosenWord,
    private val databaseRepository: IDatabaseRepository
) : BaseViewModel<WordViewState, WordViewSideEffect, WordViewAction>() {
    override fun handleAction(action: WordViewAction) {
        intent {
            when (action) {
                is Back -> postSideEffect(WordViewSideEffect.Back)
            }
        }
    }

    override val container: Container<WordViewState, WordViewSideEffect> =
        container(WordViewState()) {
            runCatching {
                databaseRepository.getWordWithLinks(chosenWord.getWord().id)
            }.onSuccess {
                reduce {
                    state.copy(
                        word = it.word.word,
                        links = it.links.map { it.link },
                        transcription = it.word.transcription,
                        translation = it.word.translation
                    )
                }
            }
        }

}
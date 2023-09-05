package com.gazim.myvocabluary.app.feature.word_test

import com.gazim.myvocabluary.app.common.BaseViewModel
import com.gazim.myvocabluary.data.ITestWordRepository
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.syntax.simple.SimpleSyntax
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

private typealias IntentScope = SimpleSyntax<WordTestState, WordTestSideEffect>

class WordTestViewModel(private val testWordRepository: ITestWordRepository) :
    BaseViewModel<WordTestState, WordTestSideEffect, WordTestAction>() {
    override fun handleAction(action: WordTestAction) {
        intent {
            when (action) {
                is WordTestAction.Back -> postSideEffect(WordTestSideEffect.Back)
                is WordTestAction.NextWord -> nextWordOrClose()
                is WordTestAction.ChangeTranscriptionVisibility -> reduce { state.copy(hiddenTranscription = !state.hiddenTranscription) }
                is WordTestAction.ChangeTranslationVisibility -> reduce { state.copy(hiddenTranslation = !state.hiddenTranslation) }
            }
        }
    }

    override val container: Container<WordTestState, WordTestSideEffect> =
        container(WordTestState()) {
            runCatching {
                testWordRepository.start()
            }.onFailure(Throwable::printStackTrace)
            nextWordOrClose()
        }

    private suspend fun IntentScope.nextWordOrClose() {
        if (!testWordRepository.hasNext()) {
            testWordRepository.finish()
            postSideEffect(WordTestSideEffect.Back)
        }
        runCatching {
            testWordRepository.nextWord()
        }.onSuccess {
            reduce {
                WordTestState(
                    word = it.word,
                    transcription = it.transcription,
                    translation = it.translation,
                )
            }
        }.onFailure(Throwable::printStackTrace)
    }
}

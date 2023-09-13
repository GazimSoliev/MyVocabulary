package com.gazim.myvocabluary.app.feature.word_test

import androidx.compose.runtime.Composable
import com.gazim.myvocabluary.app.common.BaseScreen
import com.gazim.myvocabluary.app.component.TestWordsComponent
import com.gazim.myvocabluary.app.feature.word_test.WordTestAction.Back
import com.gazim.myvocabluary.app.feature.word_test.WordTestAction.ChangeTranscriptionVisibility
import com.gazim.myvocabluary.app.feature.word_test.WordTestAction.ChangeTranslationVisibility
import com.gazim.myvocabluary.app.feature.word_test.WordTestAction.NextWord

class WordTestScreen :
    BaseScreen<WordTestState, WordTestSideEffect, WordTestAction, WordTestViewModel>(WordTestViewModel::class) {
    override suspend fun handleSideEffect(sideEffect: WordTestSideEffect) {
        when (sideEffect) {
            is WordTestSideEffect.Back -> navigator.pop()
        }
    }

    @Composable
    override fun Screen() {
        TestWordsComponent(
            word = state.word,
            transcription = state.transcription,
            translation = state.translation,
            transcriptionHidden = state.hiddenTranscription,
            translationHidden = state.hiddenTranslation,
            back = { sendAction(Back) },
            yes = { sendAction(NextWord) },
            no = { sendAction(NextWord) },
            onTranscriptionClick = { sendAction(ChangeTranscriptionVisibility) },
            onTranslationClick = { sendAction(ChangeTranslationVisibility) },
        )
    }
}

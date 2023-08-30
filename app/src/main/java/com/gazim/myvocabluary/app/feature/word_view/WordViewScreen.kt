package com.gazim.myvocabluary.app.feature.word_view

import androidx.compose.runtime.Composable
import com.gazim.myvocabluary.app.common.BaseScreen
import com.gazim.myvocabluary.app.component.LookWordComponent
import com.gazim.myvocabluary.app.feature.word_view.WordViewSideEffect.Back

class WordViewScreen :
    BaseScreen<WordViewState, WordViewSideEffect, WordViewAction, WordViewViewModel>(
        WordViewViewModel::class,
    ) {
    override suspend fun handleSideEffect(sideEffect: WordViewSideEffect) {
        when (sideEffect) {
            is Back -> {
            }
        }
    }

    @Composable
    override fun Screen() {
        LookWordComponent(
            word = state.word,
            transcription = state.transcription,
            translation = state.translation,
            linksOfPronunciation = state.links,
            back = { sendAction(WordViewAction.Back) },
        )
    }
}

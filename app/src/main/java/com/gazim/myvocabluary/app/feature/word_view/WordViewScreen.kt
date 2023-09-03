package com.gazim.myvocabluary.app.feature.word_view

import androidx.compose.runtime.Composable
import com.bumble.appyx.navigation.modality.BuildContext
import com.gazim.myvocabluary.app.common.BaseScreen
import com.gazim.myvocabluary.app.component.LookWordComponent
import com.gazim.myvocabluary.app.feature.word_view.WordViewSideEffect.Back

class WordViewScreen(buildContext: BuildContext, private val backAction: () -> Unit = {}) :
    BaseScreen<WordViewState, WordViewSideEffect, WordViewAction, WordViewViewModel>(
        buildContext,
        WordViewViewModel::class,
    ) {
    override suspend fun handleSideEffect(sideEffect: WordViewSideEffect) {
        when (sideEffect) {
            is Back -> backAction()
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

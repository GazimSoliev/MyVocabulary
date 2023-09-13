package com.gazim.myvocabluary.app.feature.word_add

import androidx.compose.runtime.Composable
import com.gazim.myvocabluary.app.common.BaseScreen
import com.gazim.myvocabluary.app.component.AddWordComponent
import com.gazim.myvocabluary.app.feature.word_add.WordAddAction.AddLink
import com.gazim.myvocabluary.app.feature.word_add.WordAddAction.EditLink
import com.gazim.myvocabluary.app.feature.word_add.WordAddAction.EditTranscription
import com.gazim.myvocabluary.app.feature.word_add.WordAddAction.EditTranslation
import com.gazim.myvocabluary.app.feature.word_add.WordAddAction.EditWord
import com.gazim.myvocabluary.app.feature.word_add.WordAddAction.Save
import com.gazim.myvocabluary.app.feature.word_add.WordAddSideEffect.Back

class WordAddScreen :
    BaseScreen<WordAddState, WordAddSideEffect, WordAddAction, WordAddViewModel>(WordAddViewModel::class) {

    override suspend fun handleSideEffect(sideEffect: WordAddSideEffect) {
        when (sideEffect) {
            is Back -> navigator.pop()
        }
    }

    @Composable
    override fun Screen() {
        AddWordComponent(
            word = state.word,
            transcription = state.transcription,
            translation = state.translation,
            linksOfPronunciation = state.links,
            addTextField = { sendAction(AddLink) },
            back = { sendAction(WordAddAction.Back) },
            save = { sendAction(Save) },
            changeWord = { sendAction(EditWord(it)) },
            changeTranscription = { sendAction(EditTranscription(it)) },
            changeTranslation = { sendAction(EditTranslation(it)) },
            changeLink = { i, v -> sendAction(EditLink(i, v)) },
        )
    }
}

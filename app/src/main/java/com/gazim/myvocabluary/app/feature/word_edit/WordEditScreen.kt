package com.gazim.myvocabluary.app.feature.word_edit

import androidx.compose.runtime.Composable
import com.gazim.myvocabluary.app.common.BaseScreen

class WordEditScreen : BaseScreen<WordEditState, WordEditSideEffect, WordEditAction, WordEditViewModel>(WordEditViewModel::class) {
    override suspend fun handleSideEffect(sideEffect: WordEditSideEffect) {
        TODO("Not yet implemented")
    }

    @Composable
    override fun Screen() {
        TODO("Not yet implemented")
    }
}

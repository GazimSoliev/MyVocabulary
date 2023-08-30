package com.gazim.myvocabluary.app.feature.word_edit

import com.gazim.myvocabluary.app.common.BaseViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.viewmodel.container

class WordEditViewModel : BaseViewModel<WordEditState, WordEditSideEffect, WordEditAction>() {
    override fun handleAction(action: WordEditAction) {
        TODO("Not yet implemented")
    }

    override val container: Container<WordEditState, WordEditSideEffect> = container(WordEditState()) {
    }
}

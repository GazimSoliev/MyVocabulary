package com.gazim.myvocabluary.app.feature.word_list

import com.gazim.myvocabluary.app.common.BaseViewModel
import com.gazim.myvocabluary.app.feature.word_list.WordListAction.*
import com.gazim.myvocabluary.data.DatabaseRepository
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class WorldListViewModel(private val databaseRepository: DatabaseRepository) : BaseViewModel<WordListState, WordListSideEffect, WordListAction>() {
    override fun handleAction(action: WordListAction) {
        intent {
            when (action) {
                is AddWord -> postSideEffect(WordListSideEffect.AddWord)
            }
        }
    }

    override val container: Container<WordListState, WordListSideEffect> = container(WordListState()) {
        runCatching {
            databaseRepository.getWords()
        }.onSuccess {
            reduce { WordListState(words = it) }
        }
    }
}

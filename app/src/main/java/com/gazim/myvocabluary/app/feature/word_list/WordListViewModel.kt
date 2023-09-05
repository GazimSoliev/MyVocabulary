package com.gazim.myvocabluary.app.feature.word_list

import com.gazim.myvocabluary.app.common.BaseViewModel
import com.gazim.myvocabluary.app.feature.word_list.WordListAction.AddWord
import com.gazim.myvocabluary.app.feature.word_list.WordListAction.RefreshList
import com.gazim.myvocabluary.app.feature.word_list.WordListAction.TestWord
import com.gazim.myvocabluary.app.feature.word_list.WordListAction.ViewWord
import com.gazim.myvocabluary.data.DatabaseRepository
import com.gazim.myvocabluary.data.IChosenWord
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.syntax.simple.SimpleSyntax
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

private typealias IntentScope = SimpleSyntax<WordListState, WordListSideEffect>

class WordListViewModel(
    private val databaseRepository: DatabaseRepository,
    private val chosenWord: IChosenWord
) : BaseViewModel<WordListState, WordListSideEffect, WordListAction>() {
    override fun handleAction(action: WordListAction) {
        intent {
            when (action) {
                is AddWord -> postSideEffect(WordListSideEffect.AddWord)
                is ViewWord -> {
                    chosenWord.setWord(action.wordId)
                    postSideEffect(WordListSideEffect.ViewWord)
                }
                is RefreshList -> loadWords()
                is TestWord -> postSideEffect(WordListSideEffect.TestWord)
            }
        }
    }

    override val container: Container<WordListState, WordListSideEffect> =
        container(WordListState()) { loadWords() }

    private suspend fun IntentScope.loadWords() {
        runCatching {
            databaseRepository.getWords()
        }.onSuccess {
            reduce { WordListState(words = it) }
        }
    }
}

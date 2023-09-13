package com.gazim.myvocabluary.app.feature.word_list

import com.gazim.myvocabluary.app.common.BaseViewModel
import com.gazim.myvocabluary.app.feature.word_list.WordListAction.AddWord
import com.gazim.myvocabluary.app.feature.word_list.WordListAction.ClearChosenWords
import com.gazim.myvocabluary.app.feature.word_list.WordListAction.DeleteChosenWords
import com.gazim.myvocabluary.app.feature.word_list.WordListAction.RefreshList
import com.gazim.myvocabluary.app.feature.word_list.WordListAction.SelectWord
import com.gazim.myvocabluary.app.feature.word_list.WordListAction.TestWord
import com.gazim.myvocabluary.app.feature.word_list.WordListAction.ToImportScreen
import com.gazim.myvocabluary.app.feature.word_list.WordListAction.UnselectWord
import com.gazim.myvocabluary.app.feature.word_list.WordListAction.ViewWord
import com.gazim.myvocabluary.app.model.IChosenWord
import com.gazim.myvocabluary.app.model.toChosenWordID
import com.gazim.myvocabluary.app.model.toWordID
import com.gazim.myvocabluary.data.DatabaseRepository
import com.gazim.myvocabluary.data.IChosenWordRepostitory
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.syntax.simple.SimpleSyntax
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

private typealias IntentScope = SimpleSyntax<WordListState, WordListSideEffect>

class WordListViewModel(
    private val databaseRepository: DatabaseRepository,
    private val chosenWord: IChosenWordRepostitory,
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
                is ToImportScreen -> postSideEffect(WordListSideEffect.ImportWords)
                is DeleteChosenWords -> {
                    databaseRepository.deleteWordsWithLinks(state.words.filterIsInstance<IChosenWord>())
                    loadWords()
                }

                is ClearChosenWords -> reduce {
                    state.copy(
                        words = state.words.map { if (it is IChosenWord) it.toWordID() else it },
                        choseMode = false,
                        chosenWordsCount = 0
                    )
                }

                is SelectWord -> reduce {
                    state.copy(
                        words = state.words.map { if (it == action.word) it.toChosenWordID() else it },
                        choseMode = true,
                        chosenWordsCount = state.chosenWordsCount + 1
                    )
                }

                is UnselectWord -> reduce {
                    state.copy(
                        words = state.words.map { if (it is IChosenWord && it == action.word) it.toWordID() else it },
                        choseMode = state.chosenWordsCount > 1,
                        chosenWordsCount = state.chosenWordsCount - 1
                    )
                }
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

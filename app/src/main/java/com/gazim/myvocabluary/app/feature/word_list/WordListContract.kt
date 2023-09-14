package com.gazim.myvocabluary.app.feature.word_list

import com.gazim.myvocabluary.app.common.IAction
import com.gazim.myvocabluary.app.common.ISideEffect
import com.gazim.myvocabluary.app.common.IState
import com.gazim.myvocabluary.app.model.IChosenWord
import com.gazim.myvocabluary.app.model.IWordID

data class WordListState(
    val words: List<IWordID> = emptyList(),
    val chosenWordsCount: Int = 0,
    val choseMode: Boolean = false,
    val canScroll: Boolean = true
) : IState

sealed interface WordListSideEffect : ISideEffect {
    data object AddWord : WordListSideEffect
    data object ViewWord : WordListSideEffect
    data object TestWord : WordListSideEffect
    data object ImportWords : WordListSideEffect
}

sealed interface WordListAction : IAction {
    data class ViewWord(val wordId: Int) : WordListAction
    data class SelectWord(val word: IWordID) : WordListAction
    data class UnselectWord(val word: IChosenWord) : WordListAction
    data object DeleteChosenWords : WordListAction
    data object ClearChosenWords : WordListAction
    data object AddWord : WordListAction
    data object RefreshList : WordListAction
    data object TestWord : WordListAction
    data object ToImportScreen : WordListAction
}

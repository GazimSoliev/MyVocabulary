package com.gazim.myvocabluary.app.feature.word_list

import com.gazim.myvocabluary.app.common.IAction
import com.gazim.myvocabluary.app.common.ISideEffect
import com.gazim.myvocabluary.app.common.IState
import com.gazim.myvocabluary.app.model.WordID

data class WordListState(
    val words: List<WordID> = emptyList(),
) : IState

sealed interface WordListSideEffect : ISideEffect {
    data object AddWord : WordListSideEffect
    data object ViewWord : WordListSideEffect
    data object TestWord : WordListSideEffect
    data object ImportWords : WordListSideEffect
}

sealed interface WordListAction : IAction {
    data object AddWord : WordListAction
    data class ViewWord(val wordId: Int) : WordListAction
    data object RefreshList : WordListAction
    data object TestWord : WordListAction
    data object ToImportScreen : WordListAction
}

package com.gazim.myvocabluary.app.feature.word_list

import androidx.compose.runtime.Composable
import com.gazim.myvocabluary.app.common.BaseScreen
import com.gazim.myvocabluary.app.component.ListOfWordsComponent
import com.gazim.myvocabluary.app.feature.import_words.ImportWordsScreen
import com.gazim.myvocabluary.app.feature.word_add.WordAddScreen
import com.gazim.myvocabluary.app.feature.word_list.WordListAction.AddWord
import com.gazim.myvocabluary.app.feature.word_list.WordListAction.DeleteWords
import com.gazim.myvocabluary.app.feature.word_list.WordListAction.RefreshList
import com.gazim.myvocabluary.app.feature.word_list.WordListAction.TestWord
import com.gazim.myvocabluary.app.feature.word_list.WordListAction.ToImportScreen
import com.gazim.myvocabluary.app.feature.word_list.WordListAction.ViewWord
import com.gazim.myvocabluary.app.feature.word_test.WordTestScreen
import com.gazim.myvocabluary.app.feature.word_view.WordViewScreen

class WordListScreen :
    BaseScreen<WordListState, WordListSideEffect, WordListAction, WordListViewModel>(
        WordListViewModel::class
    ) {

    override suspend fun handleSideEffect(sideEffect: WordListSideEffect) {
        when (sideEffect) {
            is WordListSideEffect.AddWord -> navigator.push(WordAddScreen())
            is WordListSideEffect.ViewWord -> navigator.push(WordViewScreen())
            is WordListSideEffect.TestWord -> navigator.push(WordTestScreen())
            is WordListSideEffect.ImportWords -> navigator.push(ImportWordsScreen())
        }
    }

    @Composable
    override fun Screen() {
        ListOfWordsComponent(
            words = state.words,
            addWord = { sendAction(AddWord) },
            launchTest = { sendAction(TestWord) },
            onWordClick = { sendAction(ViewWord(it)) },
            toImportScreen = { sendAction(ToImportScreen) },
            onDelete = { sendAction(DeleteWords(it)) }
        )
    }

    override fun onStart() {
        sendAction(RefreshList)
    }
}

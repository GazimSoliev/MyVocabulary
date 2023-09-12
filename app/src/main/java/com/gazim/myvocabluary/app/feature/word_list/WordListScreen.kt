package com.gazim.myvocabluary.app.feature.word_list

import androidx.compose.runtime.Composable
import com.bumble.appyx.navigation.modality.BuildContext
import com.gazim.myvocabluary.app.common.BaseScreen
import com.gazim.myvocabluary.app.component.ListOfWordsComponent
import com.gazim.myvocabluary.app.feature.word_list.WordListAction.AddWord
import com.gazim.myvocabluary.app.feature.word_list.WordListAction.RefreshList
import com.gazim.myvocabluary.app.feature.word_list.WordListAction.TestWord
import com.gazim.myvocabluary.app.feature.word_list.WordListAction.ToImportScreen
import com.gazim.myvocabluary.app.feature.word_list.WordListAction.ViewWord

class WordListScreen(
    buildContext: BuildContext,
    private val addWordAction: () -> Unit = {},
    private val viewWordAction: () -> Unit = {},
    private val testWordAction: () -> Unit = {},
    private val importWordsAction: () -> Unit = {}
) : BaseScreen<WordListState, WordListSideEffect, WordListAction, WordListViewModel>(
    buildContext,
    WordListViewModel::class,
) {

    override suspend fun handleSideEffect(sideEffect: WordListSideEffect) {
        when (sideEffect) {
            is WordListSideEffect.AddWord -> addWordAction()
            is WordListSideEffect.ViewWord -> viewWordAction()
            is WordListSideEffect.TestWord -> testWordAction()
            is WordListSideEffect.ImportWords -> importWordsAction()
        }
    }

    @Composable
    override fun Screen() {
        ListOfWordsComponent(
            words = state.words,
            addWord = { sendAction(AddWord) },
            launchTest = { sendAction(TestWord) },
            onWordClick = { sendAction(ViewWord(it)) },
            toImportScreen = { sendAction(ToImportScreen) }
        )
    }

    override fun onStart() {
        sendAction(RefreshList)
    }
}

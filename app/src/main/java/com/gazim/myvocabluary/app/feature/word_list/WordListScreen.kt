package com.gazim.myvocabluary.app.feature.word_list

import androidx.compose.runtime.Composable
import com.bumble.appyx.navigation.modality.BuildContext
import com.gazim.myvocabluary.app.common.BaseScreen
import com.gazim.myvocabluary.app.component.ListOfWordsComponent
import com.gazim.myvocabluary.app.feature.word_list.WordListAction.AddWord
import com.gazim.myvocabluary.app.feature.word_list.WordListAction.ViewWord

class WordListScreen(
    buildContext: BuildContext,
    private val addWordAction: () -> Unit = {},
    private val viewWordAction: () -> Unit = {}
) : BaseScreen<WordListState, WordListSideEffect, WordListAction, WordListViewModel>(
        buildContext,
        WordListViewModel::class
    ) {

    override suspend fun handleSideEffect(sideEffect: WordListSideEffect) {
        when (sideEffect) {
            is WordListSideEffect.AddWord -> addWordAction()
            is WordListSideEffect.ViewWord -> viewWordAction()
        }
    }

    @Composable
    override fun Screen() {
        ListOfWordsComponent(
            words = state.words,
            addWord = { sendAction(AddWord) },
            launchTest = {}, onWordClick = { sendAction(ViewWord(it)) })
    }

    override fun onStart() {
        sendAction(WordListAction.RefreshList)
    }
}

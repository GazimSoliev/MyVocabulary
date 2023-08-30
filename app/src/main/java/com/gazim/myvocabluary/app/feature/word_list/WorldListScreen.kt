package com.gazim.myvocabluary.app.feature.word_list

import androidx.compose.runtime.Composable
import com.gazim.myvocabluary.app.common.BaseScreen
import com.gazim.myvocabluary.app.component.ListOfWordsComponent

class WorldListScreen: BaseScreen<WordListState, WordListSideEffect, WordListAction, WorldListViewModel>(WorldListViewModel::class) {
    override suspend fun handleSideEffect(sideEffect: WordListSideEffect) {
        when (sideEffect) {
            is WordListSideEffect.AddWord -> {

            }
        }
    }

    @Composable
    override fun Screen() {
        ListOfWordsComponent(words = state.words, addWord = { sendAction(WordListAction.AddWord) }, launchTest = {})
    }

}
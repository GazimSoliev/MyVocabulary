package com.gazim.myvocabluary.app.navigation

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import com.gazim.myvocabluary.app.feature.word_list.WordListScreen

@Composable
fun Navigation() {
    Navigator(WordListScreen())
}

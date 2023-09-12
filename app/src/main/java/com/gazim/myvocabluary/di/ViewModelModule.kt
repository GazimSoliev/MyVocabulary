package com.gazim.myvocabluary.di

import com.gazim.myvocabluary.app.feature.import_words.ImportWordsViewModel
import com.gazim.myvocabluary.app.feature.word_add.WordAddViewModel
import com.gazim.myvocabluary.app.feature.word_list.WordListViewModel
import com.gazim.myvocabluary.app.feature.word_test.WordTestViewModel
import com.gazim.myvocabluary.app.feature.word_view.WordViewViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val viewModelModule = module {
    factoryOf(::WordListViewModel)
    factoryOf(::WordAddViewModel)
    factoryOf(::WordViewViewModel)
    factoryOf(::WordTestViewModel)
    factoryOf(::ImportWordsViewModel)
}

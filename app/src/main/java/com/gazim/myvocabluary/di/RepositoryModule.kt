package com.gazim.myvocabluary.di

import com.gazim.myvocabluary.data.ChosenWord
import com.gazim.myvocabluary.data.DatabaseRepository
import com.gazim.myvocabluary.data.IChosenWord
import com.gazim.myvocabluary.data.IDatabaseRepository
import com.gazim.myvocabluary.data.ITestWordRepository
import com.gazim.myvocabluary.data.TestWordRepository
import com.gazim.myvocabluary.data.parser.WordsParser
import com.gazim.myvocabluary.data.room.VocabularyDatabase
import com.gazim.myvocabluary.data.room.dao.VocabularyDAO
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val repositoryModule = module {
    singleOf(::TestWordRepository) bind ITestWordRepository::class
    singleOf(::ChosenWord) bind IChosenWord::class
    singleOf(::DatabaseRepository) bind IDatabaseRepository::class
    singleOf(::WordsParser)
    single<VocabularyDAO> { VocabularyDatabase.getInstance(androidContext()).vocabularyDAO() }
}

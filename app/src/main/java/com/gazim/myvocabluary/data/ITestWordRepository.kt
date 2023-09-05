package com.gazim.myvocabluary.data

import com.gazim.myvocabluary.app.model.Word

interface ITestWordRepository {
    suspend fun start()
    suspend fun hasNext(): Boolean
    suspend fun nextWord(): Word
    suspend fun finish()
}
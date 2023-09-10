package com.gazim.myvocabluary.data

import com.gazim.myvocabluary.app.model.WordID

interface ITestWordRepository {
    suspend fun start()
    suspend fun hasNext(): Boolean
    suspend fun nextWord(): WordID
    suspend fun finish()
}

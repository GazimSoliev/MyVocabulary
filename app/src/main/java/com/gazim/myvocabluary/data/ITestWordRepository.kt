package com.gazim.myvocabluary.data

import com.gazim.myvocabluary.app.model.IWordID

interface ITestWordRepository {
    suspend fun start()
    suspend fun hasNext(): Boolean
    suspend fun nextWord(): IWordID
    suspend fun finish()
}

package com.gazim.myvocabluary.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.gazim.myvocabluary.data.room.dao.VocabularyDAO
import com.gazim.myvocabluary.data.room.model.LinkDB
import com.gazim.myvocabluary.data.room.model.WordDB

@Database(entities = [WordDB::class, LinkDB::class], version = 1, exportSchema = false)
abstract class VocabularyDatabase : RoomDatabase() {
    abstract fun vocabularyDAO(): VocabularyDAO

    companion object {
        private var instance: VocabularyDatabase? = null
        fun getInstance(applicationContext: Context) {
            if (instance == null) {
                synchronized(VocabularyDatabase::class) {
                    if (instance == null) {
                        instance = Room.databaseBuilder(
                            applicationContext,
                            VocabularyDatabase::class.java,
                            "vocabulary-database",
                        ).build()
                    }
                }
            }
        }
    }
}

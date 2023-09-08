package com.gazim.myvocabluary.data.room.converter

import androidx.room.TypeConverter
import kotlinx.datetime.LocalDateTime


object LocalDateTimeConverter {
    @TypeConverter
    fun toDate(timestamp: String?): LocalDateTime? = timestamp?.let(LocalDateTime.Companion::parse)

    @TypeConverter
    fun toTimestamp(date: LocalDateTime?): String? = date?.toString()
}
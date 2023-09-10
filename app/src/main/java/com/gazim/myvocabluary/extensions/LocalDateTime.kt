package com.gazim.myvocabluary.extensions

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.toKotlinLocalDate
import kotlinx.datetime.toKotlinLocalDateTime
import kotlinx.datetime.toKotlinLocalTime

fun LocalDateTime.Companion.now(): LocalDateTime = java.time.LocalDateTime.now().toKotlinLocalDateTime()

fun LocalDate.Companion.now(): LocalDate = java.time.LocalDate.now().toKotlinLocalDate()

fun LocalTime.Companion.now(): LocalTime = java.time.LocalTime.now().toKotlinLocalTime()
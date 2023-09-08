package com.gazim.myvocabluary.extensions

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.toKotlinLocalDateTime

fun LocalDateTime.Companion.now(): LocalDateTime = java.time.LocalDateTime.now().toKotlinLocalDateTime()
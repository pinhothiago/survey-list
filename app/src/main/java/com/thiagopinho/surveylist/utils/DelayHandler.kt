package com.thiagopinho.surveylist.utils

fun interface DelayHandler {
    fun postDelayed(delayMillis: Long, block: () -> Unit)
}

// utils/Paginator.kt
package com.thiagopinho.surveylist.utils

import android.os.Handler
import android.os.Looper

class Paginator<T>(
    private val pageSize: Int,
    private val data: List<T>,
    private val onLoading: () -> Unit,
    private val onPageLoaded: (List<T>) -> Unit,
    private val delayHandler: DelayHandler = object : DelayHandler {
        override fun postDelayed(delayMillis: Long, block: () -> Unit) {
            Handler(Looper.getMainLooper()).postDelayed(block, delayMillis)
        }
    }
) {
    private var currentPage = 0
    var isLoading = false
        private set

    fun loadNextPage() {
        if (isLoading) return

        val start = currentPage * pageSize
        val end = minOf(start + pageSize, data.size)
        if (start >= end) return

        onLoading()
        isLoading = true

        delayHandler.postDelayed(400) {
            onPageLoaded(data.subList(start, end))
            currentPage++
            isLoading = false
        }
    }
}

package com.thiagopinho.surveylist.utils

class Paginator<T>(
    private val pageSize: Int,
    private val data: List<T>,
    private val onLoading: () -> Unit,
    private val onPageLoaded: (List<T>) -> Unit
) {
    private var currentPage = 0
    var isLoading = false
        private set

    fun loadNextPage() {
        if (isLoading) return

        val start = currentPage * pageSize
        val end = minOf(start + pageSize, data.size)

        if (start >= end) {
            return
        }

        onLoading()
        isLoading = true

        android.os.Handler(android.os.Looper.getMainLooper()).postDelayed({
            onPageLoaded(data.subList(start, end))
            currentPage++
            isLoading = false
        }, 400)
    }

}

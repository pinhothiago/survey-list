package com.thiagopinho.surveylist.utils

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class PaginatorTest {

    private lateinit var paginator: Paginator<String>
    private lateinit var loadedPages: MutableList<List<String>>

    @Before
    fun setup() {
        val data = List(25) { "Item $it" }
        loadedPages = mutableListOf()

        paginator = Paginator(
            pageSize = 10,
            data = data,
            onLoading = {},
            onPageLoaded = { page -> loadedPages.add(page) },
            delayHandler = DelayHandler { _, action -> action() }
        )
    }

    @Test
    fun `loadNextPage should load first page correctly`() {
        paginator.loadNextPage()

        assertEquals(1, loadedPages.size)
        assertEquals(10, loadedPages[0].size)
        assertEquals("Item 0", loadedPages[0].first())
    }

    @Test
    fun `loadNextPage should load all pages without exceeding data`() {
        repeat(3) { paginator.loadNextPage() }

        val flattened = loadedPages.flatten()
        assertEquals(25, flattened.size)
        assertEquals("Item 24", flattened.last())
    }

    @Test
    fun `loadNextPage should not load if already loading`() {
        val pendingActions = mutableListOf<() -> Unit>()

        paginator = Paginator(
            pageSize = 10,
            data = List(25) { "Item $it" },
            onLoading = {},
            onPageLoaded = { page -> loadedPages.add(page) },
            delayHandler = DelayHandler { _, action -> pendingActions.add(action) }
        )

        paginator.loadNextPage()
        paginator.loadNextPage()

        pendingActions.forEach { it() }

        assertEquals(1, loadedPages.size)
    }

}

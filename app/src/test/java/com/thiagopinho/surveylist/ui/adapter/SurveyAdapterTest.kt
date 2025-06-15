package com.thiagopinho.surveylist.ui.adapter

import com.thiagopinho.surveylist.model.Survey
import org.junit.Assert.*
import org.junit.Test

class SurveyAdapterTest {

    @Test
    fun `getItemCount should return correct size including nulls`() {
        val items = mutableListOf<Survey?>(
            Survey(1, "Pesquisa 1", 10, 100),
            null
        )
        val adapter = SurveyAdapter(items) {}
        assertEquals(2, adapter.itemCount)
    }

    @Test
    fun `getItemViewType should return correct view type`() {
        val items = mutableListOf<Survey?>(
            Survey(1, "Pesquisa 1", 10, 100),
            null
        )
        val adapter = SurveyAdapter(items) {}

        assertEquals(0, adapter.getItemViewType(0))
        assertEquals(1, adapter.getItemViewType(1))
    }
}

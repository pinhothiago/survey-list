package com.thiagopinho.surveylist.utils

import com.thiagopinho.surveylist.model.Survey

fun generateMockSurveys(total: Int = 100): List<Survey> {
    return List(total) { index ->
        Survey(
            id = index,
            title = "Pesquisa ${index + 1}",
            duration = (2..10).random(),
            reward = (3..10).random() * 10
        )
    }
}

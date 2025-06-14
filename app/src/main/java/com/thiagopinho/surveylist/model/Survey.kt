package com.thiagopinho.surveylist.model

import androidx.compose.runtime.Immutable

@Immutable
data class Survey(
    val id: Int,
    val title: String,
    val duration: Int,
    val reward: Int
)
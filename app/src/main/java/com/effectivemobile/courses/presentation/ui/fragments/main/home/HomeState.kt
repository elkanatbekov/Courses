package com.effectivemobile.courses.presentation.ui.fragments.main.home

import com.effectivemobile.domain.models.Course

sealed interface HomeState {
    data object Loading : HomeState
    data class Success(val courses: List<Course>) : HomeState
    data class Error(val message: String) : HomeState
}

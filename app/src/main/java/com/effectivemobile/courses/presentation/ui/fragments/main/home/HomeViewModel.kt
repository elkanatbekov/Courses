package com.effectivemobile.courses.presentation.ui.fragments.main.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.effectivemobile.domain.models.Course
import com.effectivemobile.domain.usecase.AddFavoriteUseCase
import com.effectivemobile.domain.usecase.DeleteFavoriteUseCase
import com.effectivemobile.domain.usecase.GetCourseUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getCourseUseCase: GetCourseUseCase,
    private val addFavoriteUseCase: AddFavoriteUseCase,
    private val deleteFavoriteUseCase: DeleteFavoriteUseCase
) : ViewModel() {

    private val _courses = MutableStateFlow<HomeState>(HomeState.Loading)
    val courses: StateFlow<HomeState> = _courses.asStateFlow()

    private var isSortedDesc = false

    init {
        loadCourses()
    }

    fun loadCourses() {
        viewModelScope.launch {
            getCourseUseCase()
                .onStart { _courses.value = HomeState.Loading }
                .catch { exception ->
                    _courses.value = HomeState.Error(exception.message ?: "Неизвестная ошибка")
                }
                .collect { courses ->
                    _courses.value = if (courses.isEmpty()) {
                        HomeState.Success(emptyList())
                    } else {
                        HomeState.Success(courses)
                    }
                }
        }
    }

    fun onBookmarkClicked(course: Course) {
        viewModelScope.launch {
            val newHasLikeState = !course.hasLike
            if (newHasLikeState) {
                addFavoriteUseCase(course.copy(hasLike = true))
            } else {
                deleteFavoriteUseCase(course)
            }

            val currentState = _courses.value
            if (currentState is HomeState.Success) {
                val updatedList = currentState.courses.map { item ->
                    if (item.id == course.id) {
                        item.copy(hasLike = newHasLikeState)
                    } else {
                        item
                    }
                }
                _courses.value = HomeState.Success(updatedList)
            }
        }
    }

    fun sortCoursesByDate() {
        val currentList = _courses.value
        if (currentList is HomeState.Success) {
            val sortedList = currentList.courses.sortedByDescending { it.sortablePublishDate ?: "" }
            _courses.value = HomeState.Success(sortedList)
            isSortedDesc = true
        }
    }
}
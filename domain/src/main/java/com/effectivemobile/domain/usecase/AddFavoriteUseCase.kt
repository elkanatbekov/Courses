package com.effectivemobile.domain.usecase

import com.effectivemobile.domain.models.Course
import com.effectivemobile.domain.repositories.CourseRepository

class AddFavoriteUseCase(private val repository: CourseRepository) {
    suspend operator fun invoke(course: Course) {
        repository.addToFavorites(course)
    }
}
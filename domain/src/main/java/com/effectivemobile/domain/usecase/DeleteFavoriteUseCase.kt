package com.effectivemobile.domain.usecase

import com.effectivemobile.domain.models.Course
import com.effectivemobile.domain.repositories.CourseRepository

class DeleteFavoriteUseCase(private val repository: CourseRepository) {
    suspend operator fun invoke(course: Course) {
        repository.removeFromFavorites(course)
    }
}
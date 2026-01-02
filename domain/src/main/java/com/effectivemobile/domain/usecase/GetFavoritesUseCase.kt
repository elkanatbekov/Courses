package com.effectivemobile.domain.usecase

import com.effectivemobile.domain.models.Course
import com.effectivemobile.domain.repositories.CourseRepository
import kotlinx.coroutines.flow.Flow

class GetFavoritesUseCase(private val repository: CourseRepository) {
    operator fun invoke(): Flow<List<Course>> {
        return repository.getFavoriteCourses()
    }
}
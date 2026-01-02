package com.effectivemobile.domain.usecase

import com.effectivemobile.domain.models.Course
import com.effectivemobile.domain.repositories.CourseRepository
import kotlinx.coroutines.flow.Flow

class GetCourseUseCase(private val courseRepository: CourseRepository) {

    operator fun invoke(): Flow<List<Course>> {
        return courseRepository.getCourses()
    }
}
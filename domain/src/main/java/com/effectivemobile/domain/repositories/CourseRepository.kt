package com.effectivemobile.domain.repositories

import com.effectivemobile.domain.models.Course
import kotlinx.coroutines.flow.Flow

interface CourseRepository {
    fun getCourses(): Flow<List<Course>>
    fun getFavoriteCourses(): Flow<List<Course>>
    suspend fun addToFavorites(course: Course)
    suspend fun removeFromFavorites(course: Course)
}

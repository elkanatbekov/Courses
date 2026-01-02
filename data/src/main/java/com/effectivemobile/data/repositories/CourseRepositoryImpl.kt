package com.effectivemobile.data.repositories

import com.effectivemobile.data.db.CourseDao
import com.effectivemobile.data.remote.apiservice.CourseApi
import com.effectivemobile.data.remote.mappers.toDomain
import com.effectivemobile.data.remote.mappers.toEntity
import com.effectivemobile.domain.models.Course
import com.effectivemobile.domain.repositories.CourseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class CourseRepositoryImpl(
    private val courseApi: CourseApi,
    private val dao: CourseDao
) : CourseRepository {
    override fun getCourses(): Flow<List<Course>> = flow {
        val response = courseApi.getCourses()
        emit(response.courses?.map {
            if (it.hasLike) dao.insertCourse(it.toEntity())
            it.toDomain()
        } ?: emptyList())
    }.flowOn(Dispatchers.IO)

    override fun getFavoriteCourses(): Flow<List<Course>> {
        return dao.getAllFavorites().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun addToFavorites(course: Course) {
        dao.insertCourse(course.toEntity())
    }

    override suspend fun removeFromFavorites(course: Course) {
        dao.deleteCourse(course.toEntity())
    }
}
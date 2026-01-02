package com.effectivemobile.data.remote.mappers

import com.effectivemobile.data.db.models.CourseEntity
import com.effectivemobile.data.remote.models.CourseDto
import com.effectivemobile.domain.models.Course
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun CourseDto.toEntity(): CourseEntity {
    return CourseEntity(
        id = this.id ?: 0,
        title = this.title ?: "Без названия",
        description = this.text ?: "",
        hasLike = this.hasLike,
        imageUrl = "",
        price = this.price ?: "",
        rate = this.rate ?: "",
        startDate = formatDate(this.startDate),
        publishDate = formatDate(this.publishDate)
    )
}

fun Course.toEntity() = CourseEntity(
    id = this.id,
    title = this.title,
    description = this.description,
    hasLike = this.hasLike,
    imageUrl = this.imageUrl,
    price = this.price,
    rate = this.rate,
    startDate = this.startDate,
    publishDate = this.publishDate
)

fun CourseEntity.toDomain() = Course(
    id = this.id,
    title = this.title,
    description = this.description,
    hasLike = this.hasLike,
    imageUrl = this.imageUrl,
    price = this.price,
    rate = this.rate,
    startDate = this.startDate,
    publishDate = this.publishDate
)

fun CourseDto.toDomain(): Course {
    return Course(
        id = this.id ?: 0,
        title = this.title ?: "Без названия",
        description = this.text ?: "",
        hasLike = this.hasLike,
        imageUrl = "",
        price = this.price ?: "",
        rate = this.rate ?: "",
        startDate = formatDate(this.startDate),
        publishDate = formatDate(this.publishDate),
        sortablePublishDate = this.publishDate
    )
}

fun formatDate(dateString: String?): String {
    if (dateString.isNullOrEmpty()) return ""

    return try {
        val inputFormat =
            SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

        val date: Date? = inputFormat.parse(dateString)
        val outputFormat = SimpleDateFormat("d MMMM yyyy", Locale("ru"))

        date?.let { outputFormat.format(it) } ?: dateString
    } catch (e: Exception) {
        dateString
    }
}

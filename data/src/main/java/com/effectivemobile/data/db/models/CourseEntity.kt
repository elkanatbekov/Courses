package com.effectivemobile.data.db.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "courses")
data class CourseEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    val description: String,
    val hasLike: Boolean = false,
    val imageUrl: String = "",
    val price: String = "",
    val rate: String = "",
    val startDate: String = "",
    val publishDate: String = ""
)

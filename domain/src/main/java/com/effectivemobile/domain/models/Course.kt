package com.effectivemobile.domain.models

data class Course(
    val id: Int,
    val title: String,
    val description: String,
    val hasLike: Boolean = false,
    val imageUrl: String = "",
    val price: String = "",
    val rate: String = "",
    val startDate: String = "",
    val publishDate: String = "",
    val sortablePublishDate: String? = null
)

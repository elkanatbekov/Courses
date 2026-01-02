package com.effectivemobile.data.remote.models

import com.google.gson.annotations.SerializedName

data class CourseResponse(
    @SerializedName("courses")
    val courses: List<CourseDto>?
)

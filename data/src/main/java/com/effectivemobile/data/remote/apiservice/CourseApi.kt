package com.effectivemobile.data.remote.apiservice

import com.effectivemobile.data.remote.models.CourseResponse
import retrofit2.http.GET

interface CourseApi {

    @GET("u/0/uc?id=15arTK7XT2b7Yv4BJsmDctA4Hg-BbS8-q&export=download")
    suspend fun getCourses(): CourseResponse
}
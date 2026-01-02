package com.effectivemobile.data.di

import androidx.room.Room
import com.effectivemobile.data.db.AppDatabase
import com.effectivemobile.data.remote.apiservice.CourseApi
import com.effectivemobile.data.repositories.CourseRepositoryImpl
import com.effectivemobile.domain.repositories.CourseRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val dataModule = module {

    single {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    single {
        OkHttpClient.Builder()
            .addInterceptor(get<HttpLoggingInterceptor>())
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl("https://drive.usercontent.google.com/")
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single { get<Retrofit>().create(CourseApi::class.java) }

    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "my_app_database.db"
        ).build()
    }

    single { get<AppDatabase>().courseDao() }

    singleOf(::CourseRepositoryImpl) { bind<CourseRepository>() }
}
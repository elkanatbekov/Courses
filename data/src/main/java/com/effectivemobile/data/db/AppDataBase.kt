package com.effectivemobile.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.effectivemobile.data.db.models.CourseEntity

@Database(entities = [CourseEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun courseDao(): CourseDao
}
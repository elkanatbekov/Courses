package com.effectivemobile.courses.presentation

import android.app.Application
import com.effectivemobile.courses.di.appModule
import com.effectivemobile.data.di.dataModule
import com.effectivemobile.domain.di.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(appModule, dataModule, domainModule)
        }
    }
}
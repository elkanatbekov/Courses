package com.effectivemobile.courses.di

import com.effectivemobile.courses.presentation.ui.fragments.main.favorite.FavoriteViewModel
import com.effectivemobile.courses.presentation.ui.fragments.main.home.HomeViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    viewModelOf(::HomeViewModel)
    viewModelOf(::FavoriteViewModel)
}
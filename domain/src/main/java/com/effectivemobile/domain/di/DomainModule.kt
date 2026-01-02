package com.effectivemobile.domain.di

import com.effectivemobile.domain.usecase.AddFavoriteUseCase
import com.effectivemobile.domain.usecase.DeleteFavoriteUseCase
import com.effectivemobile.domain.usecase.GetCourseUseCase
import com.effectivemobile.domain.usecase.GetFavoritesUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val domainModule = module {
    factoryOf(::GetCourseUseCase)
    factoryOf(::GetFavoritesUseCase)
    factoryOf(::AddFavoriteUseCase)
    factoryOf(::DeleteFavoriteUseCase)
}
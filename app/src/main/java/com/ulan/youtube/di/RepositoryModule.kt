package com.ulan.youtube.di

import com.ulan.youtube.data.repository.YoutubeRepository
import org.koin.dsl.module

val repoModule = module {
    single { YoutubeRepository(get()) }
}
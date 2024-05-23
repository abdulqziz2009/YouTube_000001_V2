package com.ulan.youtube.di

import com.ulan.youtube.ui.fragments.playlist.PlaylistViewModel
import com.ulan.youtube.ui.fragments.playlist_detail.DetailPlaylistViewModel
import org.koin.dsl.module

val uiModule = module {
    single { PlaylistViewModel(get()) }
    single { DetailPlaylistViewModel(get()) }
}
package com.ulan.youtube.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.ulan.youtube.data.model.BaseMainResponse
import com.ulan.youtube.data.model.ItemPlayList
import com.ulan.youtube.data.network.ApiService
import com.ulan.youtube.ui.utils.Resourse
import kotlinx.coroutines.Dispatchers

class YoutubeRepository(private val api: ApiService) {


    fun getPlaylist(): LiveData<Resourse<BaseMainResponse<ItemPlayList>?>> = liveData(Dispatchers.IO) {
        val result = api.getPlaylist()
        emit(Resourse.Loading())
        if (result.isSuccessful) {
            emit(Resourse.Success(data = result.body()))
        } else {
            emit(Resourse.Error(message =  result.message()))
        }

    }

    fun getPlaylistItem(id: String): LiveData<Resourse<BaseMainResponse<ItemPlayList>?>> = liveData(Dispatchers.IO) {
        val result = api.getDetailPlaylist(playlistId = id)
        emit(Resourse.Loading())
        if (result.isSuccessful) {
            emit(Resourse.Success(data = result.body()))
        } else {
            emit(Resourse.Error(message =  result.message()))
        }
    }
}
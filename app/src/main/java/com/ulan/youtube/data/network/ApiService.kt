package com.ulan.youtube.data.network

import com.ulan.youtube.BuildConfig.API_KEY
import com.ulan.youtube.BuildConfig.CHANNEL_ID
import com.ulan.youtube.data.model.BaseMainResponse
import com.ulan.youtube.data.model.ItemPlayList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("playlists")
    suspend fun getPlaylist(
        @Query("part") part: String = PART,
        @Query("channelId") channelId: String = CHANNEL_ID,
        @Query("key") key: String = API_KEY,
        @Query("maxResults") max: Int = MAX_RESULT
    ): Response<BaseMainResponse<ItemPlayList>>

    @GET("playlistItems")
suspend fun getDetailPlaylist(
    @Query("part") part: String = PART,
    @Query("key") key: String = API_KEY,
    @Query("maxResults") max: Int = MAX_RESULT,
    @Query("playlistId") playlistId: String
    ): Response<BaseMainResponse<ItemPlayList>>

    companion object {
        const val PART = "snippet,contentDetails"
        const val MAX_RESULT = 10
    }
}
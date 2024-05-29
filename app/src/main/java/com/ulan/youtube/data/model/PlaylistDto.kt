package com.ulan.youtube.data.model

import com.google.gson.annotations.SerializedName

data class BaseMainResponse<T>(
    val kind: String? = null,
    @SerializedName("etag")
    val tag: String? = null,
    val nextPageToken: String? = null,
    val prevPageToken: String? = null,
    val pageInfo: PageInfo? = null,
    val items: List<T>? = null
)

data class PageInfo(
    val totalResults: Int? = null,
    val resultsPerPage: Int? = null
)
data class ItemPlayList(
    val kind: String? = null,
    @SerializedName("etag")
    val tag: String? = null,
    val id: String? = null,
    val snippet: Snippet? = null,
    val contentDetails: ContentDetails? = null,
    val channelTitle: String? = null,
    val videoOwnerChannelTitle: String? = null,
    val resourceId: Resources? = null
)

data class Resources(
    val kind: String? = null,
    val videoId: String? = null
)

data class ContentDetails(
    val itemCount: Int? = null,
    val videoId: String? = null,
    val videoPublishedAt: String? = null,
    val startAt: String? = null,
    val endAt: String? = null
)

data class Snippet(
    val publishedAt: String? = null,
    val channelId: String? = null,
    val title: String? = null,
    val description: String? = null,
    val thumbnails: Thumb? = null,
    val channelTitle: String? = null
)

data class Thumb(
    val default: DefaultThumb? = null,
    val medium: Medium? = null
)

data class DefaultThumb(
    val url: String? = null,
    val width: Int? = null,
    val height: Int? = null
)


data class Medium(
    val url: String? = null,
    val width: Int? = null,
    val height: Int? = null
)



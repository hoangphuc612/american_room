package com.sun.americanroom.data.model

data class TopRoom (
    val id: Int?,
    val image: String?,
    val rating: Float?,
    val name: String?
)

object TopRoomEntry {
    const val LIST = "list"
    const val ID = "id"
    const val THUMBNAIL_URL = "thumbnail_url"
    const val STAR_RATING = "star_rating"
    const val NAME = "name"
}

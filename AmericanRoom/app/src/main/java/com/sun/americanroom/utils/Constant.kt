package com.sun.americanroom.utils

import com.sun.americanroom.BuildConfig

object Constant {
    const val BASE_URL = "https://mashvisor-api.p.rapidapi.com/"
    const val API_KEY = "&rapidapi-key="
    const val API_VALUE = BuildConfig.API_KEY
    const val JSON_KEY_CONTENT = "content"
    const val TREND_CITY = "trends/cities"
    const val STATE = "?state="
    const val PAGE_DEFAULT = "&page=1"
    const val TOP_REVIEW = "airbnb-property/top-reviewed"
    const val LINK_NAME = "%20"
    const val NUMBER_ITEM = "&items=5"
    const val NULL = "null"
    const val CITY = "&city="
}

object StateCode {
    const val CALIFORNIA = "CA"
    const val GEORGIA = "GA"
    const val FLORIDA = "FL"
    const val WASHINGTON = "WA"
    const val MICHIGAN = "MI"
}

object StateName {
    const val CALIFORNIA = "California"
    const val GEORGIA = "Georgia"
    const val MICHIGAN = "Michigan"
    const val WASHINGTON = "Washington"
    const val FLORIDA = "Florida"
}

object KeyEntity {
    const val CITY = "city"
    const val TOP_ROOM = "top_room"
}

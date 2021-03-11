package com.sun.americanroom.data.source.remote

import com.sun.americanroom.data.model.City
import com.sun.americanroom.data.model.TopRoom
import com.sun.americanroom.data.source.RoomDataSource
import com.sun.americanroom.data.source.remote.fetchjson.GetJsonFromUrl
import com.sun.americanroom.utils.Constant
import com.sun.americanroom.utils.KeyEntity

class RoomRemoteDataSource private constructor() : RoomDataSource.Remote {

    override fun getCity(
        listener: OnFetchDataJsonListener<MutableList<City>>,
        state: String
    ) {
        val baseUrl = Constant.BASE_URL +
            Constant.TREND_CITY +
            Constant.STATE +
            state +
            Constant.PAGE_DEFAULT +
            Constant.ITEM +
            Constant.API_KEY +
            Constant.API_VALUE
        GetJsonFromUrl(listener, KeyEntity.CITY).execute(baseUrl)
    }

    override fun getTopRoom(
        listener: OnFetchDataJsonListener<MutableList<TopRoom>>,
        state: String,
        city: String
    ) {
        val baseUrl = Constant.BASE_URL +
            Constant.TOP_REVIEW +
            Constant.STATE +
            state +
            Constant.PAGE_DEFAULT +
            Constant.CITY +
            city.replace(" ", Constant.CONSTRAINT_TEXT) +
            Constant.API_KEY +
            Constant.API_VALUE
        GetJsonFromUrl(listener, KeyEntity.TOP_ROOM).execute(baseUrl)
    }

    private object Holder {
        val INSTANCE = RoomRemoteDataSource()
    }

    companion object {
        val instance by lazy { Holder.INSTANCE }
    }
}

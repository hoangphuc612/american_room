package com.sun.americanroom.data.source

import com.sun.americanroom.data.model.City
import com.sun.americanroom.data.model.TopRoom
import com.sun.americanroom.data.source.remote.OnFetchDataJsonListener

interface RoomDataSource {

    interface Local

    interface Remote {
        fun getCity(
            listener: OnFetchDataJsonListener<MutableList<City>>,
            state: String
        )

        fun getTopRoom(
            listener: OnFetchDataJsonListener<MutableList<TopRoom>>,
            state: String,
            city: String
        )
    }
}

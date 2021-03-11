package com.sun.americanroom.data.source.repository

import com.sun.americanroom.data.model.City
import com.sun.americanroom.data.model.TopRoom
import com.sun.americanroom.data.source.RoomDataSource
import com.sun.americanroom.data.source.local.RoomLocalDataSource
import com.sun.americanroom.data.source.remote.OnFetchDataJsonListener
import com.sun.americanroom.data.source.remote.RoomRemoteDataSource

class RoomRepository private constructor(
    private val local: RoomDataSource.Local,
    private val remote: RoomDataSource.Remote
) {

    fun getCity(
        listener: OnFetchDataJsonListener<MutableList<City>>,
        state: String
    ) {
        remote.getCity(listener, state)
    }

    fun getTopRoom(
        listener: OnFetchDataJsonListener<MutableList<TopRoom>>,
        state: String,
        city: String
    ) {
        remote.getTopRoom(listener, state, city)
    }

    companion object {
        @Volatile
        private var instance: RoomRepository? = null

        fun getRepository(
            local: RoomDataSource.Local,
            remote: RoomDataSource.Remote
        ): RoomRepository =
            instance ?: synchronized(this) {
                val newInstance =
                    instance ?: RoomRepository(local, remote).also { instance = it }
                newInstance
            }
    }
}

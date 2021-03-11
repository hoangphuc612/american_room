package com.sun.americanroom.screen.top.room

import com.sun.americanroom.data.model.TopRoom
import com.sun.americanroom.utils.BasePresenter

interface TopRoomContract {

    interface View : TopRoomContract {
        fun getTopRoomOnSuccess(topRooms: MutableList<TopRoom>)
        fun onError(exception: Exception?)
    }

    interface Presenter : BasePresenter<View> {
        fun getTopRoom(state: String, city: String)
    }
}

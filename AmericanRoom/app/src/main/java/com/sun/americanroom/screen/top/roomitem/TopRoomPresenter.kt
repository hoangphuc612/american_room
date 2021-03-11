package com.sun.americanroom.screen.top.room

import com.sun.americanroom.data.model.TopRoom
import com.sun.americanroom.data.source.remote.OnFetchDataJsonListener
import com.sun.americanroom.data.source.repository.RoomRepository
import java.lang.Exception

class TopRoomPresenter(
    private val repository: RoomRepository
) : TopRoomContract.Presenter {

    private var view: TopRoomContract.View? = null

    override fun getTopRoom(state: String, city: String) {
        repository.getTopRoom(object : OnFetchDataJsonListener<MutableList<TopRoom>> {
            override fun onSuccess(data: MutableList<TopRoom>) {
                view?.getTopRoomOnSuccess(data)
            }

            override fun onError(exception: Exception?) {
                view?.onError(exception)
            }
        }, state, city)
    }

    override fun onStart() {}

    override fun onStop() {}

    override fun setView(view: TopRoomContract.View?) {
        this.view = view
    }
}

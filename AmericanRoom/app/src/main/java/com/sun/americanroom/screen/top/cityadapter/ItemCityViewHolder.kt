package com.sun.americanroom.screen.top.cityadapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.sun.americanroom.data.model.City
import com.sun.americanroom.data.model.TopRoom
import com.sun.americanroom.data.source.local.RoomLocalDataSource
import com.sun.americanroom.data.source.remote.RoomRemoteDataSource
import com.sun.americanroom.data.source.repository.RoomRepository
import com.sun.americanroom.screen.top.room.TopRoomContract
import com.sun.americanroom.screen.top.room.TopRoomPresenter
import com.sun.americanroom.screen.top.room.adapterroom.TopRoomAdapter
import kotlinx.android.synthetic.main.item_top.view.*

class ItemCityViewHolder(
    itemView: View
) : RecyclerView.ViewHolder(itemView), TopRoomContract.View {

    private val adapterTopRoom by lazy { TopRoomAdapter() }
    private val presenterItemViewHolder: TopRoomContract.Presenter by lazy {
        TopRoomPresenter(
            RoomRepository.getRepository(
                RoomLocalDataSource.instance,
                RoomRemoteDataSource.instance
            )
        )
    }

    init {
        presenterItemViewHolder.setView(this)
        with(itemView) {
            recyclerViewItemTop.apply {
                setHasFixedSize(true)
                adapter = adapterTopRoom
            }
        }
    }

    override fun getTopRoomOnSuccess(topRooms: MutableList<TopRoom>) {
        adapterTopRoom.addData(topRooms)
    }

    override fun onError(exception: Exception?) {
        exception?.printStackTrace()
    }

    fun bindViewData(city: City) = with(itemView) {
        textViewCityName.text = city.name
        presenterItemViewHolder.getTopRoom(city.state, city.name)
    }
}

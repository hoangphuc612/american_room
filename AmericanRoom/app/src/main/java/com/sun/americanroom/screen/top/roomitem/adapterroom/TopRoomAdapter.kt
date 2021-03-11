package com.sun.americanroom.screen.top.room.adapterroom

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sun.americanroom.R
import com.sun.americanroom.data.model.TopRoom

class TopRoomAdapter : RecyclerView.Adapter<ItemTopRoomViewHolder>() {

    val topRoomLists = mutableListOf<TopRoom>()

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): ItemTopRoomViewHolder =
        ItemTopRoomViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_room_horizontal, parent, false
            )
        )

    override fun getItemCount(): Int = topRoomLists.size

    override fun onBindViewHolder(holder: ItemTopRoomViewHolder, position: Int) =
        holder.bindViewData(topRoomLists[position])

    fun addData(topRoom: MutableList<TopRoom>) {
        this.topRoomLists.clear()
        this.topRoomLists.addAll(topRoom)
        notifyDataSetChanged()
    }
}

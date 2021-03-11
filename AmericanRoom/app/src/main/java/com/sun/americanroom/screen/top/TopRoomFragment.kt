package com.sun.americanroom.screen.top

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.sun.americanroom.R
import com.sun.americanroom.data.model.City
import com.sun.americanroom.data.source.local.RoomLocalDataSource
import com.sun.americanroom.data.source.remote.RoomRemoteDataSource
import com.sun.americanroom.data.source.repository.RoomRepository
import com.sun.americanroom.screen.top.cityadapter.CityAdapter
import com.sun.americanroom.utils.Constant
import com.sun.americanroom.utils.StateName
import kotlinx.android.synthetic.main.fragment_top_room.*

class TopRoomFragment : Fragment(),
    AdapterView.OnItemSelectedListener,
    TopContract.View {

    private val cityAdapter by lazy { CityAdapter() }
    private val topPresenter: TopContract.Presenter by lazy {
        TopPresenter(
            RoomRepository.getRepository(
                RoomLocalDataSource.instance,
                RoomRemoteDataSource.instance
            )
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(
            R.layout.fragment_top_room,
            container,
            false
        )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initSpinner(view)
        initPresenter()
    }

    private fun initRecyclerView() {
        recyclerViewTop.apply {
            setHasFixedSize(true)
            adapter = cityAdapter
        }
    }

    private fun initSpinner(view: View) {
        val listState = mutableListOf<String>(
            StateName.CALIFORNIA,
            StateName.GEORGIA,
            StateName.MICHIGAN,
            StateName.WASHINGTON,
            StateName.FLORIDA
        )
        val stateAdapter = ArrayAdapter<String>(
            view.context,
            R.layout.style_spinner,
            listState
        )
        spinnerPickState.adapter = stateAdapter
        spinnerPickState.onItemSelectedListener = this
    }

    override fun getCityOnSuccess(topCitys: MutableList<City>) {
        cityAdapter.addData(topCitys)
    }

    override fun onError(exception: Exception?) {
        exception?.printStackTrace()
    }

    private fun initPresenter() {
        topPresenter.apply {
            setView(this@TopRoomFragment)
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}

    override fun onItemSelected(
        parent: AdapterView<*>?,
        view: View?,
        position: Int, id: Long
    ) {
        topPresenter.getCityFromPick(parent?.selectedItem.toString())?.let {
            getCityOnSuccess(
                it
            )
        }
    }

    companion object {
        fun newInstance() = TopRoomFragment()
    }
}

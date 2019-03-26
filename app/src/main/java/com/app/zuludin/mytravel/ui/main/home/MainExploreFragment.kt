package com.app.zuludin.mytravel.ui.main.home

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.zuludin.mytravel.R
import com.app.zuludin.mytravel.data.model.local.Header
import com.app.zuludin.mytravel.data.model.local.MainExplore
import com.app.zuludin.mytravel.data.model.local.TravelMenuList
import com.app.zuludin.mytravel.data.model.remote.Weather
import com.app.zuludin.mytravel.ui.HeaderViewHolder
import com.app.zuludin.mytravel.ui.main.MainExploreViewHolder
import com.app.zuludin.mytravel.ui.main.TravelMenuViewHolder
import com.app.zuludin.mytravel.ui.main.WeatherViewHolder
import com.app.zuludin.mytravel.ui.tickets.search.SearchTravelActivity
import com.app.zuludin.mytravel.utils.DataProvider.menuList
import com.tomasznajda.simplerecyclerview.adapter.AdvancedSrvAdapter
import kotlinx.android.synthetic.main.main_explore_fragment.view.*

/**
 * A simple [Fragment] subclass.
 *
 */
class MainExploreFragment : Fragment() {

    private lateinit var itemView: View
    private lateinit var viewModel: MainExploreViewModel

    private val adapter = AdvancedSrvAdapter().apply {
        addViewHolder(Header::class, R.layout.item_header_text) { HeaderViewHolder(it) }
        addViewHolder(Weather::class, R.layout.item_weather) { WeatherViewHolder(it) }
        addViewHolder(TravelMenuList::class, R.layout.item_recycler_list) {
            TravelMenuViewHolder(it) { title ->
                val intent = Intent(itemView.context, SearchTravelActivity::class.java)
                intent.putExtra(SearchTravelActivity.TRAVEL_SEARCH, title)
                itemView.context.startActivity(intent)
                activity?.overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left)
            }
        }
        addViewHolder(MainExplore::class, R.layout.item_recycler_list) {
            MainExploreViewHolder(it) { title ->
                /*val intent = Intent(context, ExploreDetailActivity::class.java)
                intent.putExtra("exploreTitle", title)
                startActivity(intent)
                activity?.overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left)*/
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.main_explore_fragment, container, false)

        viewModel = ViewModelProviders.of(this).get(MainExploreViewModel::class.java)
        itemView = view

        adapter.set(ArrayList())

        return view
    }

    override fun onStart() {
        super.onStart()
        viewModel.getCurrentWeather()
        viewModel.getExplores()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerLayout()
    }

    private fun setupRecyclerLayout() {
        itemView.main_layout_recycler.layoutManager = LinearLayoutManager(requireContext())
        loadExploreData()
    }

    private fun loadExploreData() {
        loadCurrentWeather()
        viewModel.getExplores().observe(this, Observer {
            if (it != null) {
                adapter.insert(TravelMenuList(menuList(requireContext())))
                adapter.insert(Header("Explores"))
                adapter.insert(MainExplore(it))
                itemView.main_layout_recycler.adapter = adapter
            }
        })
    }

    private fun loadCurrentWeather() {
        viewModel.getCurrentWeather().observe(this, Observer {
            if (it != null) {
                val temperature = it.current?.temp_c.toString()
                val condition = it.location?.name.toString()
                val icon = "https:" + it.current?.condition?.icon
                adapter.insert(Weather(temperature, condition, icon), 0)
            }
        })
    }
}

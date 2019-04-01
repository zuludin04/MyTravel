package com.app.zuludin.mytravel.ui.main.home

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.app.Fragment
import android.support.v4.util.Pair
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.zuludin.mytravel.R
import com.app.zuludin.mytravel.data.model.local.CategoryItem
import com.app.zuludin.mytravel.data.model.local.CategoryList
import com.app.zuludin.mytravel.data.model.local.ExploreList
import com.app.zuludin.mytravel.data.model.remote.TravelData
import com.app.zuludin.mytravel.data.model.remote.TravelExplore
import com.app.zuludin.mytravel.ui.category.CategoryActivity
import com.app.zuludin.mytravel.ui.explore.ExploreDetailActivity
import com.app.zuludin.mytravel.ui.tickets.search.SearchTravelActivity
import com.app.zuludin.mytravel.utils.JsonUtils
import com.google.gson.GsonBuilder
import com.tomasznajda.simplerecyclerview.adapter.AdvancedSrvAdapter
import kotlinx.android.synthetic.main.main_explore_fragment.view.*
import kotlin.random.Random

/**
 * A simple [Fragment] subclass.
 *
 */
class MainExploreFragment : Fragment() {

    private val adapter = AdvancedSrvAdapter().apply {
        addViewHolder(CategoryList::class, R.layout.item_horizontal_recycler) {
            CategoryListViewHolder(it) { item, position ->
                val intent = Intent(requireContext(), CategoryActivity::class.java)
                intent.putExtra("category", item)
                intent.putExtra("position", position)
                startActivity(intent)
                activity?.overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left)
            }
        }
        addViewHolder(ExploreList::class, R.layout.item_horizontal_recycler) {
            ExploreListViewHolder(it) { image, item ->
                val intent = Intent(requireContext(), ExploreDetailActivity::class.java)
                val pair: Pair<View, String> = Pair.create(image, getString(R.string.image_transition_name))
                val options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity!!, pair)
                intent.putExtra("explore", item)
                startActivity(intent, options.toBundle())
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.main_explore_fragment, container, false)
        adapter.set(ArrayList())
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupBookingMenu(view)
        setupRecyclerLayout(view)
    }

    private fun setupBookingMenu(view: View) {
        view.book_flight.setOnClickListener { searchBookingTicket("Flight") }
        view.book_hotel.setOnClickListener { searchBookingTicket("Hotel") }
        view.book_rental.setOnClickListener { searchBookingTicket("Rental") }
        view.book_train.setOnClickListener { searchBookingTicket("Train") }
    }

    private fun setupRecyclerLayout(view: View) {
        view.recycler_explore.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            isNestedScrollingEnabled = false
            adapter = this@MainExploreFragment.adapter
        }

        adapter.insert(categoryList())
        adapter.insert(exploreItems())
    }

    private fun searchBookingTicket(search: String) {
        val intent = Intent(requireContext(), SearchTravelActivity::class.java)
        intent.putExtra(SearchTravelActivity.TRAVEL_SEARCH, search)
        startActivity(intent)
        activity?.overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left)
    }

    private fun categoryList(): CategoryList {
        val list: MutableList<CategoryItem> = mutableListOf()

        val name = arrayListOf("Beach", "Theme Park", "Museum", "Zoo", "Restaurant")
        val total = arrayListOf("30", "63", "43", "78", "72", "59", "120")

        for (i in name.indices) {
            list.add(CategoryItem(R.drawable.bali, name[i], total[i]))
        }

        return CategoryList(list, "Category")
    }

    private fun exploreItems(): List<ExploreList> {
        val data = JsonUtils.readJsonFile(requireContext(), "explore.json")
        val gson = GsonBuilder().setPrettyPrinting().create()
        val travelData: TravelData = gson.fromJson(data, TravelData::class.java)

        val list: MutableList<ExploreList> = mutableListOf()

        val random = Random(travelData.explore.size)

        val recommendations: ArrayList<TravelExplore> = ArrayList(10)
        for (i in 1..10) {
            recommendations.add(travelData.explore[random.nextInt(travelData.explore.size)])
        }

        val topSpot: ArrayList<TravelExplore> = ArrayList(10)
        for (i in 1..10) {
            topSpot.add(travelData.explore[random.nextInt(travelData.explore.size)])
        }

        val recent: ArrayList<TravelExplore> = ArrayList(10)
        for (i in 1..10) {
            recent.add(travelData.explore[random.nextInt(travelData.explore.size)])
        }

        list.add(ExploreList(recommendations, "Recommendations"))
        list.add(ExploreList(topSpot, "Top Spots"))
        list.add(ExploreList(recent, "Recent Place"))

        return list
    }
}

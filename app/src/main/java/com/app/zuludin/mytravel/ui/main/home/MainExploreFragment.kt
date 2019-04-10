package com.app.zuludin.mytravel.ui.main.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.app.zuludin.mytravel.R
import com.app.zuludin.mytravel.data.model.local.CategoryList
import com.app.zuludin.mytravel.data.model.remote.ExploreList
import com.app.zuludin.mytravel.ui.category.CategoryActivity
import com.app.zuludin.mytravel.ui.explore.ExploreDetailActivity
import com.app.zuludin.mytravel.ui.tickets.search.SearchTravelActivity
import com.app.zuludin.mytravel.utils.DataProvider.categoryList
import com.app.zuludin.mytravel.utils.ViewModelFactory
import com.tomasznajda.simplerecyclerview.adapter.AdvancedSrvAdapter
import kotlinx.android.synthetic.main.main_explore_fragment.view.*

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
                intent.putExtra("title", item.name)
                intent.putExtra("dataId", item.dataId)
                startActivity(intent, options.toBundle())
            }
        }
    }

    private val viewModel: MainExploreViewModel by lazy {
        ViewModelProviders.of(this, ViewModelFactory.getInstance(activity?.application!!))
            .get(MainExploreViewModel::class.java)
    }

    private lateinit var itemView: View
    private lateinit var explores: java.util.ArrayList<ExploreList>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.main_explore_fragment, container, false)
        itemView = view
        explores = java.util.ArrayList()
        adapter.set(ArrayList())
        return view
    }

    override fun onStart() {
        super.onStart()
        itemView.shimmer_view_container.startShimmerAnimation()
    }

    override fun onStop() {
        itemView.shimmer_view_container.stopShimmerAnimation()
        super.onStop()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList(EXPLORE_LIST, explores)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupBookingMenu(view)
        setupRecyclerLayout(view, savedInstanceState)
    }

    private fun setupBookingMenu(view: View) {
        view.book_flight.setOnClickListener { searchBookingTicket("Flight") }
        view.book_hotel.setOnClickListener { searchBookingTicket("Hotel") }
        view.book_rental.setOnClickListener { searchBookingTicket("Rental") }
        view.book_train.setOnClickListener { searchBookingTicket("Train") }
    }

    private fun setupRecyclerLayout(view: View, savedInstanceState: Bundle?) {
        view.recycler_explore.apply {
            layoutManager = androidx.recyclerview.widget.LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            isNestedScrollingEnabled = false
            adapter = this@MainExploreFragment.adapter
        }

        if (savedInstanceState != null) {
            adapter.insert(categoryList())
            val data: java.util.ArrayList<ExploreList> = savedInstanceState.getParcelableArrayList(EXPLORE_LIST)!!
            adapter.insert(data)
            view.recycler_explore.adapter = adapter
            view.shimmer_view_container.stopShimmerAnimation()
            view.shimmer_view_container.visibility = View.GONE
        } else {
            viewModel.getExplores().observe(this, Observer {
                if (it != null) {
                    explores.addAll(it)
                    adapter.insert(categoryList())
                    adapter.insert(it)
                    view.recycler_explore.adapter = adapter
                    view.shimmer_view_container.stopShimmerAnimation()
                    view.shimmer_view_container.visibility = View.GONE
                }
            })
        }
    }

    private fun searchBookingTicket(search: String) {
        val intent = Intent(requireContext(), SearchTravelActivity::class.java)
        intent.putExtra(SearchTravelActivity.TRAVEL_SEARCH, search)
        startActivity(intent)
        activity?.overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left)
    }

    companion object {
        private const val EXPLORE_LIST = "explore"
    }
}

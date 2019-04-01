package com.app.zuludin.mytravel.ui.category

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.app.Fragment
import android.support.v4.util.Pair
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.zuludin.mytravel.R
import com.app.zuludin.mytravel.data.model.remote.TravelData
import com.app.zuludin.mytravel.data.model.remote.TravelExplore
import com.app.zuludin.mytravel.ui.explore.ExploreDetailActivity
import com.app.zuludin.mytravel.utils.JsonUtils.readJsonFile
import com.google.gson.GsonBuilder
import com.tomasznajda.simplerecyclerview.adapter.AdvancedSrvAdapter
import kotlinx.android.synthetic.main.category_list_fragment.view.*

/**
 * A simple [Fragment] subclass.
 *
 */
class CategoryListFragment : Fragment() {

    private val adapter: AdvancedSrvAdapter by lazy {
        AdvancedSrvAdapter().apply {
            addViewHolder(TravelExplore::class, R.layout.item_explore_place) {
                CategoryViewHolder(it) { image, item ->
                    val intent = Intent(requireContext(), ExploreDetailActivity::class.java)
                    val pair: Pair<View, String> = Pair.create(image, getString(R.string.image_transition_name))
                    val options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity!!, pair)
                    intent.putExtra("explore", item)
                    startActivity(intent, options.toBundle())
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.category_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter.set(ArrayList())

        val category = arguments?.getString(PAGER_CATEGORY)

        val data = readJsonFile(requireContext(), "explore.json")
        val gson = GsonBuilder().setPrettyPrinting().create()
        val travelData: TravelData = gson.fromJson(data, TravelData::class.java)

        val explores: MutableList<TravelExplore> = mutableListOf()
        explores.addAll(travelData.explore.filter { it.category == category }.toList())

        view.recycler_category_explore.apply {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            setHasFixedSize(true)
            adapter = this@CategoryListFragment.adapter
        }

        adapter.insert(explores)
    }

    companion object {
        private const val PAGER_CATEGORY = "pagerCategory"

        fun getInstance(title: String): CategoryListFragment {
            val fragment = CategoryListFragment()
            val args = Bundle()
            args.putString(PAGER_CATEGORY, title)
            fragment.arguments = args
            return fragment
        }
    }
}

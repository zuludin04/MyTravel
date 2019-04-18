package com.app.zuludin.mytravel.ui.main.favorite

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
import androidx.recyclerview.widget.GridLayoutManager
import com.app.zuludin.mytravel.R
import com.app.zuludin.mytravel.data.model.local.Favourite
import com.app.zuludin.mytravel.ui.explore.ExploreDetailActivity
import com.app.zuludin.mytravel.utils.begone
import com.app.zuludin.mytravel.utils.visible
import com.tomasznajda.simplerecyclerview.adapter.AdvancedSrvAdapter
import kotlinx.android.synthetic.main.main_favorite_fragment.view.*

/**
 * A simple [Fragment] subclass.
 *
 */
class MainFavoriteFragment : Fragment() {

    private val adapter: AdvancedSrvAdapter by lazy {
        AdvancedSrvAdapter().apply {
            addViewHolder(Favourite::class, R.layout.item_explore_place) {
                FavouriteViewHolder(it) { image, item ->
                    val intent = Intent(requireContext(), ExploreDetailActivity::class.java)
                    val pair: Pair<View, String> = Pair.create(image, getString(R.string.image_transition_name))
                    val options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity!!, pair)
                    intent.putExtra("title", item.name)
                    intent.putExtra("dataId", item.dataId?.toInt())
                    startActivity(intent, options.toBundle())
                }
            }
        }
    }

    private val viewModel: MainFavouriteViewModel by lazy {
        ViewModelProviders.of(this).get(MainFavouriteViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.main_favorite_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter.set(ArrayList())
        setupFavouriteList(view)
    }

    private fun setupFavouriteList(view: View) {
        view.recycler_favourite.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            setHasFixedSize(true)
            adapter = this@MainFavoriteFragment.adapter
        }

        viewModel.loadFavouritePlaces().observe(this, Observer {
            it?.let { favourites ->
                if (favourites.isEmpty()) {
                    view.recycler_favourite.begone()
                } else {
                    adapter.insert(favourites)
                    view.recycler_favourite.adapter = adapter
                    view.empty_icon.begone()
                    view.empty_message.visible()
                }
            }
        })
    }
}

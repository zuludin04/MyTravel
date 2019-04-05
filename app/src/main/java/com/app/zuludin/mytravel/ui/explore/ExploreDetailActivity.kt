package com.app.zuludin.mytravel.ui.explore

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.transition.Slide
import android.view.Gravity
import android.view.MenuItem
import com.app.zuludin.mytravel.R
import com.app.zuludin.mytravel.data.model.remote.TravelExplore
import com.app.zuludin.mytravel.databinding.ExploreDetailActivityBinding
import com.app.zuludin.mytravel.utils.ViewModelFactory
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.explore_detail_activity.*

class ExploreDetailActivity : AppCompatActivity() {

    private val viewModel: ExploreDetailViewModel by lazy {
        ViewModelProviders.of(this, ViewModelFactory.getInstance(application)).get(ExploreDetailViewModel::class.java)
    }

    private lateinit var binding: ExploreDetailActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.explore_detail_activity)

        val explore: TravelExplore = intent.getParcelableExtra("explore")

        loadExploreData(explore)
        windowsAnimation()
        setupToolbar(explore)
    }

    private fun setupToolbar(explore: TravelExplore) {
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            title = explore.name
            setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun loadExploreData(explore: TravelExplore) {
        viewModel.getDetail(explore.dataId!!).observe(this, Observer { travel ->
            travel?.let {
                Picasso.get().load(it.thumbnail).into(binding.detailExploreImage)
                binding.explore = it
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                finishAfterTransition()
            } else {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun windowsAnimation() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val slide = Slide()
            slide.slideEdge = Gravity.BOTTOM

            slide.excludeTarget(toolbar, true)
            slide.excludeTarget(android.R.id.navigationBarBackground, true)
            slide.excludeTarget(android.R.id.statusBarBackground, true)

            window.enterTransition = slide
            window.exitTransition = slide
        }
    }
}

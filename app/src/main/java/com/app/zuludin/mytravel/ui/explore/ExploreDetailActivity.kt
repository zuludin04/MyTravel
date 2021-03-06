package com.app.zuludin.mytravel.ui.explore

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.transition.Slide
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.app.zuludin.mytravel.R
import com.app.zuludin.mytravel.databinding.ExploreDetailActivityBinding
import com.app.zuludin.mytravel.ui.common.ReviewAdapter
import com.app.zuludin.mytravel.ui.explore.gallery.ExploreFullGalleryActivity
import com.app.zuludin.mytravel.utils.ViewModelFactory
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.explore_detail_activity.*

class ExploreDetailActivity : AppCompatActivity() {

    private val viewModel: ExploreDetailViewModel by lazy {
        ViewModelProviders.of(this, ViewModelFactory.getInstance(application)).get(ExploreDetailViewModel::class.java)
    }

    private lateinit var binding: ExploreDetailActivityBinding
    private var isAboutExpanded: Boolean = false
    private var menuItem: Menu? = null
    private var isFavourite = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.explore_detail_activity)

        val exploreId = intent.getIntExtra("dataId", 0)
        isFavourite = viewModel.isFavourite(exploreId.toString())

        loadExploreData(exploreId)
        windowsAnimation()
        setupToolbar()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.favourite_menu, menu)
        menuItem = menu
        setFavouriteIcon()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                finishAfterTransition()
            } else {
                finish()
            }
        } else if (item.itemId == R.id.favorite_menu) {
            if (isFavourite) {
                viewModel.removeFavourite()
                showSnackbar("Favourite is Removed")
                setResult(Activity.RESULT_OK)
            } else {
                viewModel.favouritePlace()
                showSnackbar("Favourite is Added")
            }
            isFavourite = !isFavourite
            setFavouriteIcon()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setFavouriteIcon() {
        if (isFavourite) {
            favIcon(R.drawable.ic_favorite)
        } else {
            favIcon(R.drawable.ic_favorite_border)
        }
    }

    private fun favIcon(drawable: Int) {
        menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(applicationContext, drawable)
    }

    private fun showSnackbar(message: String) {
        Snackbar.make(toolbar, message, Snackbar.LENGTH_SHORT).show()
    }

    private fun setupToolbar() {
        val name = intent.getStringExtra("title")
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            title = name
            setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun loadExploreData(dataId: Int) {
        viewModel.getDetail(dataId).observe(this, Observer { travel ->
            travel?.let {
                Picasso.get().load(it.thumbnail).into(binding.detailExploreImage)
                binding.explore = it
                it.review?.let { review ->
                    review_pager.adapter = ReviewAdapter(review)
                }
            }
        })

        expand_about.setOnClickListener {
            if (!isAboutExpanded) {
                binding.detailExploreAbout.maxLines = 10
                expand_about.text = getString(R.string.less)
                isAboutExpanded = true
            } else {
                binding.detailExploreAbout.maxLines = 3
                expand_about.text = getString(R.string.more)
                isAboutExpanded = false
            }
        }

        gallery_image.setOnClickListener {
            startActivity(
                Intent(
                    applicationContext,
                    ExploreFullGalleryActivity::class.java
                )
            )
        }
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

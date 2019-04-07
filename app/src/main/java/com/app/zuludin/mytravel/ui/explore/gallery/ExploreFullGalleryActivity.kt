package com.app.zuludin.mytravel.ui.explore.gallery

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import android.view.MotionEvent
import android.view.View
import com.app.zuludin.mytravel.R
import kotlinx.android.synthetic.main.explore_full_gallery_activity.*

class ExploreFullGalleryActivity : AppCompatActivity() {

    private val hideHandler = Handler()
    private var isContentVisible: Boolean = false
    private val hideRunnable = Runnable { fullscreenMode() }

    private val showFullscreenRunnable = Runnable {
        gallery_pager.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LOW_PROFILE or
                    View.SYSTEM_UI_FLAG_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
    }

    private val hideFullscreenRunnable = Runnable {
        supportActionBar?.show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.explore_full_gallery_activity)

        setupToolbar()
        setupContentLayout()
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            title = "Gallery"
            setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun setupContentLayout() {
        isContentVisible = true

        val list: MutableList<Int> = mutableListOf()
        for (i in 1..4) {
            list.add(R.drawable.bali)
        }
        gallery_pager.adapter = GalleryAdapter(list)
        gallery_pager.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                toggle()
            }
            false
        }
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        delayHide(250)
    }

    private fun toggle() {
        if (isContentVisible) {
            fullscreenMode()
        } else {
            toolbarMode()
        }
    }

    private fun fullscreenMode() {
        supportActionBar?.hide()
        isContentVisible = false

        hideHandler.removeCallbacks(hideFullscreenRunnable)
        hideHandler.postDelayed(showFullscreenRunnable, UI_ANIMATION_DELAY.toLong())
    }

    private fun toolbarMode() {
        gallery_pager.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        isContentVisible = true

        hideHandler.removeCallbacks(showFullscreenRunnable)
        hideHandler.postDelayed(hideFullscreenRunnable, UI_ANIMATION_DELAY.toLong())
    }

    private fun delayHide(delayMillis: Int) {
        hideHandler.removeCallbacks(hideRunnable)
        hideHandler.postDelayed(hideRunnable, delayMillis.toLong())
    }

    companion object {
        private const val UI_ANIMATION_DELAY = 300
    }
}

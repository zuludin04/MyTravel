package com.app.zuludin.mytravel.ui.explore.gallery

import androidx.viewpager.widget.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.zuludin.mytravel.R
import kotlinx.android.synthetic.main.item_full_gallery.view.*

class GalleryAdapter(private val items: List<Int>) : PagerAdapter() {

    override fun isViewFromObject(p0: View, p1: Any): Boolean = p0 == p1

    override fun getCount(): Int = items.size

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(container.context).inflate(R.layout.item_full_gallery, container, false)
        view.gallery_full.setImageResource(items[position])
        container.addView(view)
        return view
    }
}
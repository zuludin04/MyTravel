package com.app.zuludin.mytravel.ui.common

import androidx.viewpager.widget.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.zuludin.mytravel.R
import com.app.zuludin.mytravel.data.model.remote.Review
import kotlinx.android.synthetic.main.item_review.view.*

class ReviewAdapter(private val items: List<Review>) : PagerAdapter() {

    override fun isViewFromObject(p0: View, p1: Any): Boolean = p0 == p1

    override fun getCount(): Int = items.size

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(container.context).inflate(R.layout.item_review, container, false)

        val review = items[position]
        view.review_name.text = review.name
        view.review_comment.text = review.comment
        view.review_date.text = review.date
        review.rating?.let { view.review_rating.rating = it.toFloat() }

        container.addView(view)
        return view
    }
}
package com.app.zuludin.mytravel.ui.category

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.ViewPager
import com.app.zuludin.mytravel.R
import com.app.zuludin.mytravel.data.model.local.CategoryItem
import kotlinx.android.synthetic.main.category_activity.*

class CategoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.category_activity)

        setSupportActionBar(toolbar)
        supportActionBar?.title = ""

        val category: CategoryItem = intent.getParcelableExtra("category")
        toolbar_title.text = category.item
        toolbar_image.setImageResource(category.image)
        back_button.setOnClickListener { onBackPressed() }

        val pagerAdapter = CategoryPagerAdapter(supportFragmentManager)
        category_pager.adapter = pagerAdapter
        tabs.setupWithViewPager(category_pager)

        val position = intent.getIntExtra("position", 0)
        category_pager.currentItem = position

        category_pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(p0: Int) {}

            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {}

            override fun onPageSelected(position: Int) {
                val icon = arrayListOf(R.drawable.beach, R.drawable.theme_park, R.drawable.museum, R.drawable.zoo, R.drawable.restaurant)
                val pagerCategory = arrayListOf("Beach", "Theme Park", "Museum", "Zoo", "Restaurant")
                toolbar_title.text = pagerCategory[position]
                toolbar_image.setImageResource(icon[position])
            }
        })
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_right)
    }
}

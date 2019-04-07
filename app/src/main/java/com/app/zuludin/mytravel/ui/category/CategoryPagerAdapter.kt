package com.app.zuludin.mytravel.ui.category

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class CategoryPagerAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager) {

    private val pagerCategory = arrayListOf("Beach", "Theme Park", "Museum", "Zoo", "Restaurant")

    override fun getItem(position: Int): androidx.fragment.app.Fragment =
        CategoryListFragment.getInstance(pagerCategory[position])

    override fun getCount(): Int = pagerCategory.size

    override fun getPageTitle(position: Int): CharSequence? = pagerCategory[position]
}
package com.app.zuludin.mytravel.ui.explore

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.app.zuludin.mytravel.R
import com.app.zuludin.mytravel.data.model.local.ExploreItem
import kotlinx.android.synthetic.main.explore_detail_activity.*

class ExploreDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.explore_detail_activity)

        val explore: ExploreItem = intent.getParcelableExtra("explore")

        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            title = explore.name
            setDisplayHomeAsUpEnabled(true)
        }

        detail_explore_image.setImageResource(explore.image)
        detail_explore_name.text = explore.name
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) finish()
        return super.onOptionsItemSelected(item)
    }
}

package com.app.zuludin.mytravel.utils

import android.graphics.Rect
import androidx.recyclerview.widget.RecyclerView
import android.view.View

class SpacingItemDecoration(
    private val spacing: Int
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        outRect.left = spacing
        outRect.right = spacing
        outRect.bottom = spacing

        if (parent.getChildLayoutPosition(view) == 0) {
            outRect.top = spacing
        } else {
            outRect.top = 0
        }
    }
}
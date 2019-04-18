package com.app.zuludin.mylibrary.floathint.spinner

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.PopupWindow
import android.widget.TextView
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.zuludin.mylibrary.R
import com.app.zuludin.mylibrary.floathint.postOnMainThread
import kotlinx.android.synthetic.main.floating_hint_spinner.view.*

class SpinnerFloatingHint(
    context: Context,
    attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {

    private var hintTranslationX = -1
    private var hintTranslationY = -1
    private var displayHeight: Int = 0
    private var parentVerticalOffset: Int = 0
    private val verticalOffset = 1

    private var hintText: String? = ""
        set(value) {
            field = value
            spinner_label.postOnMainThread {
                spinner_container.postOnMainThread {
                    spinner_label.text = hintText ?: ""
                    spinner_label.hint = ""
                }
            }
        }

    init {
        initLayout(attrs)
    }

    private fun initLayout(attrs: AttributeSet?) {
        View.inflate(context, R.layout.floating_hint_spinner, this)
        setupView()
        attrs?.let {
            getAttributes(it)
        }
        displayHeight = resources.displayMetrics.heightPixels
    }

    private fun setupView() {
        val rv = RecyclerView(context)
        val popup = PopupWindow(context)
        val data: TextView = findViewById(R.id.spinner_data)

        spinner_container.setOnClickListener { showPopup(rv, popup) }

        rv.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = FloatingSpinnerAdapter(context, items()) { item, position ->
                if (position == 0) {
                    hideHint()
                    popup.dismiss()
                } else {
                    showHint()
                    data.text = item
                    popup.dismiss()
                }
            }
        }
    }

    private fun showPopup(rv: RecyclerView, popup: PopupWindow) {
        measurePopupDimension(rv, popup)
        popup.contentView = rv
        popup.isOutsideTouchable = true
        popup.isFocusable = true
        popup.showAsDropDown(this)
    }

    private fun getAttributes(attrs: AttributeSet) {
        val styled = context.obtainStyledAttributes(attrs, R.styleable.SpinnerFloatingHint)
        try {
            hintText = styled.getString(R.styleable.SpinnerFloatingHint_spinnerHint) ?: ""
        } finally {
            styled.recycle()
        }
    }

    private fun hideHint() {
        spinner_label.apply {
            postOnMainThread {
                ViewCompat.animate(this)
                    .alpha(1f)
                    .scaleX(1f)
                    .scaleY(1f)
                    .translationY(0f)
                    .translationX(0f).duration = 400.toLong()
            }
            setTextColor(resources.getColor(android.R.color.darker_gray))
        }

        spinner_data.postOnMainThread {
            ViewCompat.animate(spinner_data)
                .alpha(0f)
                .duration = 400.toLong()
        }
    }

    private fun showHint() {
        val miniatureScale = 0.8f
        spinner_label.apply {
            postOnMainThread {
                ViewCompat.animate(this)
                    .alpha(1f)
                    .scaleX(miniatureScale)
                    .scaleY(miniatureScale)
                    .translationY(-hintTranslationY.toFloat())
                    .translationX(-hintTranslationX.plus(0).times(miniatureScale)).duration = 400.toLong()
            }
            setTextColor(resources.getColor(R.color.secondaryColor))
        }

        spinner_data.postOnMainThread {
            ViewCompat.animate(spinner_data)
                .alpha(1f)
                .duration = 400.toLong()
        }
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        spinner_label.postOnMainThread {
            spinner_label.pivotX = 0f
            spinner_label.pivotY = 0f
            hintTranslationY = (spinner_label.layoutParams as? FrameLayout.LayoutParams)?.topMargin ?: 0
            hintTranslationX = spinner_label.paddingStart
            spinner_label.bringToFront()
        }

        spinner_container.postOnMainThread {
            hideHint()
        }
    }

    private fun measurePopupDimension(rv: RecyclerView, popup: PopupWindow) {
        val widthSpec = MeasureSpec.makeMeasureSpec(measuredWidth, MeasureSpec.EXACTLY)
        val heightSpec = MeasureSpec.makeMeasureSpec(getPopupHeight(), MeasureSpec.AT_MOST)
        rv.measure(widthSpec, heightSpec)
        popup.width = rv.measuredWidth
        popup.height = rv.measuredHeight
    }

    private fun getPopupHeight(): Int = Math.max(verticalSpaceBelow(), verticalSpaceAbove())

    private fun verticalSpaceAbove(): Int = getParentVerticalOffset()

    private fun verticalSpaceBelow(): Int = displayHeight - getParentVerticalOffset() - measuredHeight

    private fun getParentVerticalOffset(): Int {
        if (parentVerticalOffset > 0) {
            return parentVerticalOffset
        }
        val locationOnScreen = IntArray(2)
        getLocationOnScreen(locationOnScreen)

        parentVerticalOffset = locationOnScreen[verticalOffset]

        return parentVerticalOffset
    }

    private fun items(): MutableList<String> {
        val list: MutableList<String> = mutableListOf()

        val array = context.resources.getStringArray(R.array.id_type)
        for (i in array.indices) {
            list.add(array[i])
        }

        return list
    }
}
package com.app.zuludin.mylibrary.dropdown

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.app.zuludin.mylibrary.R
import com.apps.zuludin.mylibrary.dropdown.SelectedItemListener

class FloatingLabelDropdown(context: Context, attrs: AttributeSet?) : LinearLayout(context, attrs) {

    private lateinit var mDropdownLabel: TextView
    lateinit var dropdownItem: TextView
    private lateinit var mFloatLabelIcon: ImageView
    private lateinit var mDropdownIcon: ImageView
    private lateinit var mContainer: RelativeLayout
    private lateinit var mRoot: ViewGroup
    private lateinit var mDropdownList: RecyclerView
    private lateinit var mLabel: String

    private lateinit var mItem: String
    private lateinit var mIcon: Drawable
    private var mDropdownMode: Boolean = false
    private var mItemList: MutableList<String> = ArrayList()

    private lateinit var mPopupWindow: PopupWindow

    private var listener: SelectedItemListener? = null

    private val verticalOffset = 1
    private val defaultPopupElevation = 8
    private var parentVerticalOffset: Int = 0
    private var displayHeight: Int = 0

    init {
        parseAttrs(context, attrs)
        inflateLayout(context)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (mDropdownMode && isEnabled && event.action == MotionEvent.ACTION_UP) {
            if (!mPopupWindow.isShowing) {
                showPopupWindow()
            } else {
                hidePopupWindow()
            }
        }
        return super.onTouchEvent(event)
    }

    private fun parseAttrs(context: Context, attrs: AttributeSet?) {
        val ta = context.obtainStyledAttributes(attrs, R.styleable.FloatingLabelDropdown)
        try {
            mLabel = ta.getString(R.styleable.FloatingLabelDropdown_dropdownLabel)
            mItem = ta.getString(R.styleable.FloatingLabelDropdown_dropdownItem)
            mIcon = ta.getDrawable(R.styleable.FloatingLabelDropdown_dropdownIcon)
            mDropdownMode = ta.getBoolean(R.styleable.FloatingLabelDropdown_dropdownMode, false)
        } finally {
            ta.recycle()
        }

        isClickable = mDropdownMode
        displayHeight = resources.displayMetrics.heightPixels
    }

    private fun inflateLayout(context: Context) {
        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.float_label_dropdown, this, true)
        mDropdownLabel = findViewById(R.id.float_label)
        dropdownItem = findViewById(R.id.float_value)
        mFloatLabelIcon = findViewById(R.id.float_icon)
        mDropdownIcon = findViewById(R.id.dropdown_icon)
        mContainer = findViewById(R.id.container)
        mDropdownList = androidx.recyclerview.widget.RecyclerView(context)
        mRoot = this

        bindView()
    }

    private fun bindView() {
        mDropdownLabel.text = mLabel
        dropdownItem.text = mItem
        mFloatLabelIcon.setImageDrawable(mIcon)

        mDropdownList.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
        mDropdownList.addItemDecoration(
            androidx.recyclerview.widget.DividerItemDecoration(
                context,
                androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
            )
        )
        mDropdownList.adapter = DropdownListAdapter(context, mItemList) {
            dropdownItem.text = it
            listener?.onItemSelected(it)
            mPopupWindow.dismiss()
        }

        mPopupWindow = PopupWindow(context)
        mPopupWindow.contentView = mDropdownList
        mPopupWindow.isOutsideTouchable = true
        mPopupWindow.isFocusable = true

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mPopupWindow.elevation = defaultPopupElevation.toFloat()
            mPopupWindow.setBackgroundDrawable(
                ContextCompat.getDrawable(
                    context,
                    R.drawable.spinner_drawable
                )
            );
        } else {
            mPopupWindow.setBackgroundDrawable(
                ContextCompat.getDrawable(
                    context,
                    R.drawable.drop_down_shadow
                )
            );
        }

        if (mDropdownMode) {
            mDropdownIcon.visibility = View.VISIBLE
        } else {
            mDropdownIcon.visibility = View.GONE
        }
    }

    private fun showPopupWindow() {
        measurePopupDimension()
        mPopupWindow.showAsDropDown(this)
    }

    private fun measurePopupDimension() {
        val widthSpec = MeasureSpec.makeMeasureSpec(measuredWidth, MeasureSpec.EXACTLY)
        val heightSpec = MeasureSpec.makeMeasureSpec(getPopupHeight(), MeasureSpec.AT_MOST)
        mDropdownList.measure(widthSpec, heightSpec)
        mPopupWindow.width = mDropdownList.measuredWidth
        mPopupWindow.height = mDropdownList.measuredHeight
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

    private fun hidePopupWindow() {
        mPopupWindow.dismiss()
    }

    fun addDropdownItem(items: List<String>) {
        mItemList.addAll(items)
    }

    fun changeDropdownItem(text: String) {
        dropdownItem.text = text
    }

    fun getItemText(): String {
        return dropdownItem.text.toString()
    }

    fun setSelectedListener(listener: SelectedItemListener) {
        this.listener = listener
    }
}

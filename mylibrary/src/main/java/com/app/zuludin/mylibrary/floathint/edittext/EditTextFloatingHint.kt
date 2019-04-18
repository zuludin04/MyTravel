package com.app.zuludin.mylibrary.floathint.edittext

import android.content.Context
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import androidx.core.view.ViewCompat
import com.app.zuludin.mylibrary.R
import com.app.zuludin.mylibrary.floathint.postOnMainThread
import kotlinx.android.synthetic.main.floating_hint_edit_text.view.*

class EditTextFloatingHint(
    context: Context,
    attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {

    private var hintTranslationX = -1
    private var hintTranslationY = -1

    private var etHint: String? = null
        set (value) {
            field = value
            edit_text_hint.postOnMainThread {
                edit_text_item.postOnMainThread {
                    edit_text_hint.text = etHint ?: ""
                    edit_text_hint.hint = ""
                }
            }
        }

    init {
        initLayout(attrs)
    }

    private fun initLayout(attrs: AttributeSet?) {
        View.inflate(context, R.layout.floating_hint_edit_text, this)
        setupView()
        attrs?.let {
            getAttributes(it)
        }
    }

    private fun setupView() {
        edit_text_item.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (TextUtils.isEmpty(s)) {
                    hideHint()
                } else {
                    showHint()
                }
            }
        })
    }

    private fun getAttributes(attrs: AttributeSet) {
        val styled = context.obtainStyledAttributes(attrs, R.styleable.EditTextFloatingHint)
        try {
            etHint = styled.getString(R.styleable.EditTextFloatingHint_android_hint) ?: ""
        } finally {
            styled.recycle()
        }
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        edit_text_hint.postOnMainThread {
            edit_text_hint.pivotX = 0f
            edit_text_hint.pivotY = 0f
            hintTranslationY = (edit_text_hint.layoutParams as? FrameLayout.LayoutParams)?.topMargin ?: 0
            hintTranslationX = edit_text_hint.paddingStart
            edit_text_hint.bringToFront()
        }

        edit_text_item.postOnMainThread {
            edit_text_item.clearFocus()
            edit_text_item.bringToFront()

            if (edit_text_item.text.toString().isNotBlank()) {
                hideHint()
            }
        }
    }

    private fun hideHint() {
        edit_text_hint.apply {
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
    }

    private fun showHint() {
        val floatingSize = 0.8f
        edit_text_hint.apply {
            postOnMainThread {
                ViewCompat.animate(this)
                    .alpha(1f)
                    .scaleY(floatingSize)
                    .scaleX(floatingSize)
                    .translationY(-hintTranslationY.toFloat())
                    .translationX(-hintTranslationX.plus(0).times(floatingSize)).duration = 400.toLong()
            }
            setTextColor(resources.getColor(R.color.secondaryColor))
        }
    }
}
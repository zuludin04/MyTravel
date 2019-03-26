package com.app.zuludin.mylibrary.backdrop

import android.content.Context
import android.support.annotation.IdRes
import android.support.design.widget.CoordinatorLayout
import android.util.AttributeSet
import android.view.View

class BackdropLayout : CoordinatorLayout.Behavior<View> {

    constructor() : super()

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    private var backdropLayoutId: Int? = null

    private var child: View? = null
    private var backdropContainer: View? = null

    private var dropState: DropState = DEFAULT_DROP_DATE

    override fun layoutDependsOn(parent: CoordinatorLayout, child: View, dependency: View): Boolean {
        if (backdropLayoutId == null) return false

        return when (dependency.id) {
            backdropLayoutId -> true
            else -> false
        }
    }

    override fun onDependentViewChanged(parent: CoordinatorLayout, child: View, dependency: View): Boolean {
        this.child = child

        when (dependency.id) {
            backdropLayoutId -> backdropContainer = dependency
        }

        if (backdropContainer != null) {
            initView(parent, child, backdropContainer!!)
        }

        return super.onDependentViewChanged(parent, child, dependency)
    }

    fun open(withAnimation: Boolean = true): Boolean = if (dropState == DropState.OPEN) {
        false
    } else {
        dropState = DropState.OPEN
        if (child != null && backdropContainer != null) {
            drawDropState(child!!, backdropContainer!!, withAnimation)
        } else {
            throw IllegalArgumentException("BackdropLayout must be initialized")
        }
        true
    }

    fun close(withAnimation: Boolean = true): Boolean = if (dropState == DropState.CLOSE) {
        false
    } else {
        dropState = DropState.CLOSE
        if (child != null && backdropContainer != null) {
            drawDropState(child!!, backdropContainer!!, withAnimation)
        } else {
            throw IllegalArgumentException("BackdropLayout must be initialized")
        }
        true
    }

    fun attachBackdropLayout(@IdRes layoutSource: Int) {
        backdropLayoutId = layoutSource
    }

    fun setDropState(dropState: DropState) {
        this.dropState = dropState
    }

    private fun initView(parent: CoordinatorLayout, child: View, backdropContainer: View) {
        child.layoutParams.height = parent.height
        drawDropState(child, backdropContainer, false)
    }

    private fun drawDropState(child: View, backdropContainer: View, withAnimation: Boolean) {
        when (dropState) {
            DropState.OPEN -> {
                drawOpenState(child, backdropContainer, withAnimation)
            }
            DropState.CLOSE -> {
                drawCloseState(child, backdropContainer, withAnimation)
            }
        }
    }

    private fun drawCloseState(child: View, backdropContainer: View, withAnimation: Boolean) {
        val position = backdropContainer.y
        val duration = if (withAnimation) 300L else 0L

        child.animate().y(position).setDuration(duration).start()
    }

    private fun drawOpenState(child: View, backdropContainer: View, withAnimation: Boolean) {
        val position = backdropContainer.y + backdropContainer.height
        val duration = if (withAnimation) 300L else 0L

        child.animate().y(position).setDuration(duration).start()
    }

    companion object {
        private val DEFAULT_DROP_DATE = DropState.CLOSE
    }
}
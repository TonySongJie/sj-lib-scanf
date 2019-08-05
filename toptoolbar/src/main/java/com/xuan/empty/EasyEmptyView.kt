package com.xuan.empty

import android.content.Context
import android.support.annotation.DrawableRes
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.widget.Button

class EasyEmptyView : Button {

    private val mContext: Context

    private var mEmptyIcon = 0
    private var mEmptyType = EmptyType.BUTTON

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, def: Int) : super(context, attrs, def) {
        mContext = context

        val typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.EasyEmptyView)
        mEmptyIcon = typedArray.getResourceId(R.styleable.EasyEmptyView_empty_icon, R.drawable.icon_empty_default)

        when (typedArray.getInteger(R.styleable.EasyEmptyView_empty_type, 1)) {
            2 -> {
                EmptyType.VIEW
                setEmptyView()
            }
            else -> {
                EmptyType.BUTTON
                setEmptyButton()
            }
        }

        typedArray.recycle()

        setEmptyIcon(mEmptyIcon)
        text = "这里是文字区域，请设置你要显示"
    }

    fun setEmptyIcon(@DrawableRes icon: Int) {
        this.mEmptyIcon = icon

        if (mEmptyIcon != 0) {
            val topDrawable = ContextCompat.getDrawable(mContext, mEmptyIcon)
            val intrinsicWidth = topDrawable?.intrinsicWidth!!
            val intrinsicHeight = topDrawable.intrinsicHeight

            val left = if (width > intrinsicWidth) (width - intrinsicWidth) / 2 else 0
            val top = if (height > intrinsicHeight) (height - intrinsicHeight) / 2 else 0

            topDrawable.setBounds(
                left,
                top,
                intrinsicWidth,
                intrinsicHeight
            )
            setCompoundDrawables(null, topDrawable, null, null)
            compoundDrawablePadding = 12

            invalidate()
        }
    }

    fun setEmptyButton() {
        this.mEmptyType = EmptyType.BUTTON
        isEnabled = true

        invalidate()
    }

    fun setEmptyView() {
        this.mEmptyType = EmptyType.VIEW
        isEnabled = false

        invalidate()
    }

    fun addOnClickListener(listener: OnClickListener?) {
        if (listener != null && isEnabled) {
            setOnClickListener(listener)
        }
    }

    enum class EmptyType {
        BUTTON, VIEW
    }
}
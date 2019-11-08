package com.app.adak.ui.customWidget

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.app.adak.R


class TextViewAdak : AppCompatTextView {
    lateinit var customFont: String


    constructor(context: Context) : super(context) {


    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        style(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        style(context, attrs)
    }

    private fun style(context: Context, attrs: AttributeSet) {

        val a = context.obtainStyledAttributes(attrs, R.styleable.CustomFontTextView)
        val cf = a.getInteger(R.styleable.CustomFontTextView_fontName, 0)

        customFont = when (cf) {
            1 -> "IRANSans(FaNum)_Bold"
            2 -> "IRANSans(FaNum)_Light"
            3 -> "IRANSans(FaNum)_Medium"

            else -> "IRANSans(FaNum)_Light"
        }
        val tf = Typeface.createFromAsset(context.assets, "fonts/$customFont.ttf")
        typeface = tf
        a.recycle()
    }

    companion object {
        val TAG = TextViewAdak::class.java.toString() + "debug"
    }
}

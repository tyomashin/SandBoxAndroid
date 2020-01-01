package com.tyomashin.customviewssample.horizontalcardscrollsample

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MotionEvent
import android.widget.LinearLayout
import com.tyomashin.customviewssample.R

/**
 * CardView をイメージしたカスタムレイアウト.
 * これだけだと用途はないが、View をグループ化して流用的に使用できる.
 * */

class CustomCardView(context: Context, attrs : AttributeSet)
    : LinearLayout(context, attrs){

    private var listener : OnClickListener? = null

    init {
        LayoutInflater.from(this.context).inflate(R.layout.custom_card_view, this)
    }

    override fun setOnClickListener(l: OnClickListener?) {
        listener = l
    }

    // クリック処理を有効にするために必要
    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (ev?.action == MotionEvent.ACTION_UP) listener?.onClick(this)
        return super.dispatchTouchEvent(ev)
    }

}
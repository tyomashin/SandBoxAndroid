package com.example.uimocksample.Views.SimpleCircleView

import android.content.Context
import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.ColorFilter
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.ShapeDrawable
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.example.uimocksample.Views.SimpleCustomView.SimpleCustomView
import com.example.uimocksample.databinding.CustomViewSimpleBinding
import com.example.uimocksample.databinding.CustomViewSimpleCircleBinding

/**
 * シンプルな円のカスタムView.
 */
class SimpleCircleView (
    context: Context,
    attrs: AttributeSet
) : LinearLayout(context, attrs){

    private var _binding: CustomViewSimpleCircleBinding? = null
    private val binding get() = _binding!!

    init {
        // カスタムViewでViewBindingする方法
        // https://stackoverflow.com/questions/60433269/how-to-use-view-binding-on-custom-views
        _binding = CustomViewSimpleCircleBinding.inflate(LayoutInflater.from(context), this, true)
    }

    /**
     * 背景色を設定する
     * 参考：https://stackoverflow.com/questions/17823451/set-android-shape-color-programmatically
     */
    fun setCircleBackgroundColor(bigCircleColor: Int, smallCircleColor: Int){
        /*
        val bigCircleDrawable = binding.bigCircle.background as? GradientDrawable
        Log.d("hoge!", "setCircleBackgroundColor")
        println("hoge, ${binding.bigCircle.background}")
        bigCircleDrawable?.let{
            Log.d("hoge!", "big!")
            it.setColor(ContextCompat.getColor(context, bigCircleColor))
        }
        val smallCircleDrawable = binding.smallCircle.background as? GradientDrawable
        smallCircleDrawable?.let {
            Log.d("hoge!", "small!")
            it.setColor(ContextCompat.getColor(context, smallCircleColor))
        }
        */
        /**
         * 上の方法は冗長な方法。
         * 参考サイトより、以下のシンプルな方法を採用
         */
        binding.bigCircle.background.colorFilter = BlendModeColorFilter(ContextCompat.getColor(context, bigCircleColor), BlendMode.SRC_ATOP)
        binding.smallCircle.background.colorFilter = BlendModeColorFilter(ContextCompat.getColor(context, smallCircleColor), BlendMode.SRC_ATOP)
    }
}
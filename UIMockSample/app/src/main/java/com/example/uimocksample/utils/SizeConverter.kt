package com.example.uimocksample.utils

import android.app.Application
import android.content.Context
import android.util.DisplayMetrics
import com.example.uimocksample.MyApplication
import kotlin.math.roundToInt

/**
 * サイズ変換を担うオブジェクト
 */
object SizeConverter{
    /**
     * Pixel を DP に変換する
     * memo: Pixel は LayoutParams に指定する単位
     * 参考：https://stackoverflow.com/questions/8309354/formula-px-to-dp-dp-to-px-android/8490361
     */
    fun convertPixelToDp(pixel: Int) : Int {
        val context = MyApplication.applicationContext()
        return if (context != null) {
            val displayMetrics = context.resources.displayMetrics
            (pixel / (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT)).roundToInt()
        }else{
            pixel
        }
    }

    /**
     * DP を Pixel に変換する
     */
    fun convertDpToPixel(dp: Int) : Int {
        val context = MyApplication.applicationContext()
        return if (context != null) {
            val displayMetrics = context.resources.displayMetrics
            (dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT)).roundToInt()
        }else{
            dp
        }
    }
}
package com.example.uimocksample.entity

import android.content.Context
import android.media.Image
import com.example.uimocksample.R

/**
 * 本アプリで用意しているUIコンポーネントの情報
 */
data class UiComponentData(val id: Int, val name: String, val transitionMode: UIListTransitionMode) {}

/**
 * 画面遷移種別
 */
enum class UIListTransitionMode{
    SimpleCustomView,
    BottomTabWithFragmentView,
    Carousel;

    // 名称
    fun title(context: Context?): String{
        return when(this){
            SimpleCustomView -> context?.getString(R.string.title_simple_custom_view) ?: ""
            BottomTabWithFragmentView -> context?.getString(R.string.title_bottom_tab_fragment_view) ?: ""
            Carousel -> context?.getString(R.string.title_carousel_ui_sample) ?: ""
            else -> ""
        }
    }
}
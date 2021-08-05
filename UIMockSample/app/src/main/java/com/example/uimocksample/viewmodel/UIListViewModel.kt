package com.example.uimocksample.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.uimocksample.R
import com.example.uimocksample.entity.UiComponentData

class UIListViewModel(): ViewModel() {
    // private var componentList: Array<String> = []

    /**
     * コンポーネントリストに表示するアイテムを返す
     */
    fun getComponentList(context: Context?): List<UiComponentData> {
        val data1 = UiComponentData(id = 1, name = context?.getString(R.string.title_carousel_ui_sample) ?: "")
        val data2 = UiComponentData(id = 2, name = "2")
        return listOf(data1, data2)
    }

    /**
     * アイテムタップイベント
     */
    fun tapComponentItem(position: Int){
        Log.d("hoge", position.toString())

        // TODO: 画面遷移処理

    }
}
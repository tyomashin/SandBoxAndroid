package com.example.uimocksample.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.uimocksample.R
import com.example.uimocksample.entity.UIListTransitionMode
import com.example.uimocksample.entity.UiComponentData
import com.example.uimocksample.extensions.singleLiveData

class UIListViewModel(): ViewModel() {
    /**
     * 画面遷移フラグ
     * memo: 普通にLiveDataを使うと、遷移先から戻ってきた場合も再度最新値が通知され、
     * もう一度画面遷移が走ってしまうと言う問題がある。
     * このため以下を参考に実装している
     * https://qiita.com/shiita0903/items/5a3db085c7a64a752af6#singleliveevent2
     * https://satoshun.github.io/2018/05/singlelivedata/
     */
    private val transitionModeData = singleLiveData<UIListTransitionMode>()
    val transitionMode: LiveData<UIListTransitionMode> = transitionModeData

    private var uiList = mutableListOf<UiComponentData>()

    /**
     * コンポーネントリストに表示するアイテムを返す
     */
    fun getComponentList(context: Context?): List<UiComponentData> {
        val result = mutableListOf<UiComponentData>()
        UIListTransitionMode.values().forEach { mode ->
            val data = UiComponentData(
                id = mode.ordinal,
                name = mode.title(context),
                transitionMode = mode
            )
            result.add(data)
        }
        this.uiList = result
        return result.toList()
    }

    /**
     * アイテムタップイベント
     */
    fun tapComponentItem(position: Int){
        // 画面遷移処理
        val targetData = uiList[position]
        transitionModeData.postValue(targetData.transitionMode)
    }
}
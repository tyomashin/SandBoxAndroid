package com.example.uimocksample.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*

class SplashViewModel: ViewModel(){

    companion object{
        const val WAIT_TIME: Long = 2000
    }

    enum class SplashTransitionMode{
        UIList
    }

    /**
     * 画面遷移フラグ
     */
    private val transitionModeData = MutableLiveData<SplashTransitionMode?>()
    val transitionMode: LiveData<SplashTransitionMode?> = transitionModeData

    /**
     * Activity 開始時に呼ばれる
     */
    fun startActivity(){
        // サブスレッドで処理するコルーチン
        CoroutineScope(Dispatchers.Default).launch {
            Log.d("hoge", "start wait")
            // 一定秒数待機
            delay(WAIT_TIME)
            Log.d("hoge", "end wait")

            // 画面遷移処理
            // memo: 非同期で値をセット
            // https://stackoverflow.com/questions/53304347/mutablelivedata-cannot-invoke-setvalue-on-a-background-thread-from-coroutine
            transitionModeData.postValue(SplashTransitionMode.UIList)
            // memo: 以下はバックグラウンドスレッドではアプリが落ちる
            // transitionModeData.value = SplashTransitionMode.UIList
        }
    }
}
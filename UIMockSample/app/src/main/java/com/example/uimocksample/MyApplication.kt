package com.example.uimocksample

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate

/**
 * 独自に定義したアプリケーションクラス
 * 各シングルトンクラスなど、アプリケーションと同じライフサイクルを持つ主体でContextが必要な場合はここから取り出す
 * 参考：https://stackoverflow.com/questions/54075649/access-application-context-in-companion-object-in-kotlin
 */
class MyApplication: Application(){
    init {
        instance = this
        Log.d("hoge", "MyApplication init")
    }

    companion object{
        private var instance: MyApplication? = null

        fun applicationContext() : Context? {
            return instance?.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        // ダークテーマ無効
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

    }
}

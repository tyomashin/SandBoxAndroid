package com.tyomashin.pushnotificationsampleandroid

import android.content.Context
import android.graphics.BitmapFactory

// bitmap の上にテキストを描画する
// 参考：https://medium.com/@travells2323/android-draw-text-to-bitmap-8251f6d79150
class BitMapManager(val context : Context) {

    fun hoge(){
        val r = context.resources

        var bitmap = BitmapFactory.decodeResource(r, R.mipmap.ic_launcher)
        var bitmapConfig = bitmap.config;
        // set default bitmap config if none
        if (bitmapConfig == null) {
            bitmapConfig = android.graphics.Bitmap.Config.ARGB_8888
        }
        bitmap = bitmap.copy(bitmapConfig, true)
    }

}
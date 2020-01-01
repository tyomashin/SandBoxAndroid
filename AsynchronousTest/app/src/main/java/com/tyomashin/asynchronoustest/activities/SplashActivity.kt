package com.tyomashin.asynchronoustest.activities

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity

/*
* アプリ起動時の初期画面。
* ：前処理やルーティングをここで行う。
* ：ユーザの時間を取らないように、レイアウトファイルをインフレートせずに
* 　ActivityのThemeにDrawableリソースを使う。
* 　　・background_splash.xml　でDrawableリソース作成
* 　　・styles.xml で カスタムThemeを作成。
* 　　　android:windowBackground　で背景に Drawable リソースを指定。
*
* 参考：
* ・https://www.bignerdranch.com/blog/splash-screens-the-right-way/
* ・https://qiita.com/glayash/items/646e5c0d5de82cfc17bc
* */

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

}
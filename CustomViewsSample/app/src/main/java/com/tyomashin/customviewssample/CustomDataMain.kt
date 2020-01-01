package com.tyomashin.customviewssample

import android.support.v7.app.AppCompatActivity

// out (宣言箇所分散パラメータ) をつけることで、AppCompatActivity のサブクラス(MainActivityなど)をプロパティとして渡せる。
// 本来、ジェネリクスは不変であり、例えば List<String> は List<Object> のサブクラスではないため、推論による代入ができない。
// 参照：https://dogwood008.github.io/kotlin-web-site-ja/docs/reference/generics.html

data class CustomDataMain (val titleId : Int,
                           val activityClass : Class<out AppCompatActivity>)
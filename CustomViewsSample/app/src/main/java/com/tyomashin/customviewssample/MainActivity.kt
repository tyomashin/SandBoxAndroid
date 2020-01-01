package com.tyomashin.customviewssample

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.tyomashin.customviewssample.horizontalcardscrollsample.HorizontalCardScrollActivity

/**
 * カスタムView のサンプルをまとめる（予定）のギャラリープロジェクト.
 * */

class MainActivity : AppCompatActivity() {

    companion object{
        val demos = arrayListOf(
            CustomDataMain(R.string.horizontal_card_scroll_activity, HorizontalCardScrollActivity::class.java),
            CustomDataMain(R.string.test_activity, HorizontalCardScrollActivity::class.java)
        )
    }

    lateinit var recyclerView : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = with(findViewById<RecyclerView>(R.id.recyclerView)){
            layoutManager = LinearLayoutManager(context)
            adapter = CustomAdapterMain(demos, this@MainActivity)

            this
        }
    }

    // recyclerView のアイテムがクリックされた時の処理
    fun clickListItem(targetItem : CustomDataMain){
        startActivity(Intent(this, targetItem.activityClass))
    }
}
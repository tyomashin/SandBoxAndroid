package com.example.android.walkthroughsample.common

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.res.ResourcesCompat
import android.support.v4.view.ViewPager
import android.view.View
import android.widget.LinearLayout
import com.example.android.walkthroughsample.R
import com.example.android.walkthroughsample.walkthrogh.CustomAdapter
import com.example.android.walkthroughsample.walkthrogh.WalkThroughType

class MainActivity : AppCompatActivity() {

    var scale : Float = 0.0f

    lateinit var viewPager: ViewPager
    lateinit var viewPagerAdapter : CustomAdapter
    lateinit var indicatorArea : LinearLayout
    private var indicatorViewList : ArrayList<View> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        scale = resources.displayMetrics.density

        viewPager = findViewById(R.id.viewPager)
        viewPagerAdapter = CustomAdapter(supportFragmentManager)
        viewPager.adapter = viewPagerAdapter
        viewPager.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener(){
            override fun onPageSelected(position: Int) {
                for (i in 0 until indicatorViewList.size){
                    if (i == position)
                        indicatorViewList[i].background = ResourcesCompat.getDrawable(resources,R.drawable.indicator_view_active, null)
                    else
                        indicatorViewList[i].background = ResourcesCompat.getDrawable(resources,R.drawable.indicator_view_inactive, null)

                }
            }
        })

        //ウォークスルーのページ数だけインディケータを表示
        indicatorArea = findViewById(R.id.indicator_area)

        val indicatorWidth = resources.getDimension(R.dimen.walk_through_indicator_size).toInt()
        val indicatorHeight = resources.getDimension(R.dimen.walk_through_indicator_size).toInt()
        val indicatorMarginStart = resources.getDimension(R.dimen.walk_through_indicator_margin_start).toInt()
        for (i in 0 until WalkThroughType.values().size){
            var view = View(this)
            if (i == 0){
                view.background = getDrawable(R.drawable.indicator_view_active)
                val layoutParams = LinearLayout.LayoutParams(indicatorWidth, indicatorHeight)
                view.layoutParams = layoutParams
            }else {
                view.background = getDrawable(R.drawable.indicator_view_inactive)
                val layoutParams = LinearLayout.LayoutParams(indicatorWidth,indicatorHeight)
                layoutParams.marginStart = indicatorMarginStart
                view.layoutParams = layoutParams
            }
            indicatorArea.addView(view)
            indicatorViewList.add(view)
        }
    }
}

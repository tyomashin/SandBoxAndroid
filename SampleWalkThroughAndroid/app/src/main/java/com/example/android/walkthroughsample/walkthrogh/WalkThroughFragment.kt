package com.example.android.walkthroughsample.walkthrogh

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.example.android.walkthroughsample.R

class WalkThroughFragment : Fragment(){

    var walkThroughType : WalkThroughType? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.walkthrough_fragment, container, false)
    }

    /*
    * View の初期化。
    *
    * 表示するフラグメントの種類（WalkThroughType）に応じて View を変更している。
    * */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.takeIf { it.containsKey(WalkThroughTypeKey) }?.apply {
            walkThroughType = WalkThroughType.values().mapNotNull {
                if (getInt(WalkThroughTypeKey) == it.ordinal) it else null}.first()
            initWalkThroughPage(view)
        }

    }

    private fun initWalkThroughPage(argView : View) {

        val linearLayout : FrameLayout = argView.findViewById(R.id.frame_layout)
        val imageView : ImageView = argView.findViewById(R.id.imageView)
        val textView : TextView = argView.findViewById(R.id.title)

        when (walkThroughType){
            WalkThroughType.First -> {
                linearLayout.setBackgroundResource(R.color.walk_through_1)
                imageView.setImageResource(R.mipmap.fragment1)
                textView.text = getText(R.string.first_fragment_title)
            }
            WalkThroughType.Second -> {
                linearLayout.setBackgroundResource(R.color.walk_through_2)
                imageView.setImageResource(R.mipmap.fragment2)
                textView.text = getText(R.string.second_fragment_title)
            }
            WalkThroughType.Third -> {
                linearLayout.setBackgroundResource(R.color.walk_through_3)
                imageView.setImageResource(R.mipmap.fragment3)
                textView.text = getText(R.string.third_fragment_title)
            }
        }

    }

}
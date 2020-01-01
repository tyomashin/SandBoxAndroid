package com.example.android.walkthroughsample.walkthrogh

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

//ウォークスルーに表示するフラグメントの種類
enum class WalkThroughType{
    First,
    Second,
    Third
}

const  val WalkThroughTypeKey = "WalkThroughType"

class CustomAdapter (fm : FragmentManager) : FragmentPagerAdapter(fm){

    override fun getCount(): Int = WalkThroughType.values().size

    override fun getItem(position: Int): Fragment {
        val fragment = WalkThroughFragment()
        fragment.arguments = Bundle().apply {
            putInt(WalkThroughTypeKey,
                WalkThroughType.values().mapNotNull { if (position == it.ordinal) it.ordinal else null}.first())
        }
        return fragment
    }
}
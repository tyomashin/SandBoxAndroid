package com.tyomashin.pushnotificationsampleandroid

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Log.d("hoge",intent.extras["localNotification"]!!.toString())

        button1.setOnClickListener {
            // 通知を発行する
            PushNotificationManager(this).showLocalNotification("title", "description")
        }
    }
}

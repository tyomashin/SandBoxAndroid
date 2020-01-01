package com.tyomashin.asynchronoustest.asyncTasks

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.tyomashin.asynchronoustest.activities.MainActivity

class BroadcastReceiverSample(private val activity : MainActivity) : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {

        if (intent?.action == MainActivity.ACTION_INTENT_SERVICE) {
            Log.d("BroadcastReceiver", "Main")
            activity.runOnUiThread {
                activity.finishIntentServiceTask(intent.getBooleanExtra("flag", false))
            }
        }else{
            Log.d("BroadcastReceiver", "False")
        }
    }

}
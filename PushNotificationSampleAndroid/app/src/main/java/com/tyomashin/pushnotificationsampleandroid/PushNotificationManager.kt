package com.tyomashin.pushnotificationsampleandroid

import android.app.*
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import android.util.Log
import com.google.firebase.iid.FirebaseInstanceId



class PushNotificationManager(val context : Context){
    companion object {
        private const val CHANNEL_ID = "CHANNEL_ID"
        private const val CHANNEL_NAME = "CHANNEL_NAME"
        private const val CHANNEL_DESCRIPTION = "CHANNEL_DESCRIPTION"
    }

    // ローカル通知を発行する
    fun showLocalNotification(title : String, description : String){
        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        createNotificationChannel(manager)

        val notificationBuilder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(title)
            .setContentText(description)

        createPendingIntent()?.let {
            notificationBuilder.setContentIntent(it)
        }
        val notification = notificationBuilder.build()
        notification.flags = Notification.DEFAULT_LIGHTS or Notification.FLAG_AUTO_CANCEL
        manager.notify(0, notification)
    }

    // チャネルを作成する
    // 参考：https://developer.android.com/training/notify-user/channels?hl=ja
    private fun createNotificationChannel(manager: NotificationManager) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            if (manager.getNotificationChannel(CHANNEL_ID) == null) {
                val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT)
                channel.description = CHANNEL_DESCRIPTION
                manager.createNotificationChannel(channel)
            }
        }
    }
    // 通知タップ時に動作させるためにPendingIntentを作成する
    // 参考：https://joyplot.com/documents/2018/04/27/kotlin-android-notification/
    // https://developer.android.com/training/notify-user/navigation#kotlin
    // https://qiita.com/SnowMonkey/items/7cdc52c83bb9490d97a5
    // 考察すること：https://codeday.me/jp/qa/20181210/56536.html
    private fun createPendingIntent() : PendingIntent?{
        val resultIntent = Intent(context,MainActivity::class.java)
        resultIntent.putExtra("localNotification","0")
        val resultPendingIntent: PendingIntent? = TaskStackBuilder.create(context).run {
            // Add the intent, which inflates the back stack
            addNextIntentWithParentStack(resultIntent)
            // Get the PendingIntent containing the entire back stack
            getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
        }
        return resultPendingIntent
    }

    fun testToken(){
        FirebaseInstanceId.getInstance().instanceId.addOnCompleteListener { task ->
            Log.d("testToken", task.result?.token)
        }
    }


}
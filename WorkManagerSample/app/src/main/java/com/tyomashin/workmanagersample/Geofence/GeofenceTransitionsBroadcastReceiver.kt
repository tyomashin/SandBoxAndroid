package com.tyomashin.workmanagersample.Geofence

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.support.v4.app.NotificationCompat
import android.support.v4.content.ContextCompat.getSystemService
import android.util.Log
import com.google.android.gms.location.Geofence
import com.google.android.gms.location.GeofencingEvent
import com.tyomashin.workmanagersample.R

// ジオフェンスエリアにチェックインした際の受信処理
// 参考：https://qiita.com/shiita0903/items/fe1a27b2619c5ea7fdef
class GeofenceTransitionsBroadcastReceiver : BroadcastReceiver(){

    override fun onReceive(context: Context?, intent: Intent?) {
        context ?: return
        val geofencingEvent = GeofencingEvent.fromIntent(intent)

        when (geofencingEvent.geofenceTransition){
            Geofence.GEOFENCE_TRANSITION_ENTER -> sendNotification(context,"Enter")
            Geofence.GEOFENCE_TRANSITION_EXIT -> sendNotification(context,"Exit")
            else -> sendNotification(context,"error")
        }
    }

    // プッシュ通知配信
    private fun sendNotification(context : Context, text: String) {
        //val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        createNotificationChannel(manager)

        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setStyle(NotificationCompat.BigTextStyle())
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Geofence")
            .setContentText(text)
            .build()
        manager.notify(0, notification)
    }

    private fun createNotificationChannel(manager: NotificationManager) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            if (manager.getNotificationChannel(CHANNEL_ID) == null) {
                val channel = NotificationChannel(CHANNEL_ID, "name", NotificationManager.IMPORTANCE_HIGH)
                channel.description = "description"
                manager.createNotificationChannel(channel)
            }
        }
    }

    companion object {
        private const val CHANNEL_ID = "channelId"
    }
}
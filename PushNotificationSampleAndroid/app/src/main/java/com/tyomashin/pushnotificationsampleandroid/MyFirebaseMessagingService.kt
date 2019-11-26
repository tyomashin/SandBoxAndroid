package com.tyomashin.pushnotificationsampleandroid

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

/**
 * FCM の通知を受信するクラス
 * https://firebase.google.com/docs/cloud-messaging/android/receive?hl=ja
 */
class MyFirebaseMessagingService : FirebaseMessagingService(){

    /**
     * 通知を受信するクラス.
     *
     */
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        // データメッセージ
        remoteMessage.data.isEmpty().let {
            PushNotificationManager(applicationContext).showLocalNotification("datamessage","data catch")
            Log.d("datamessage","catch")
            //remoteMessage.data.
        }

        // 通知メッセージ
        remoteMessage.notification?.let {
            PushNotificationManager(applicationContext).showLocalNotification("notificationmessage","message catch")
            Log.d("notificationmessage","catch")
        }
    }

    /**
     * トークンを監視
     */
    override fun onNewToken(token: String) {
        super.onNewToken(token)

        Log.d("onnewtoken",token)
    }
}
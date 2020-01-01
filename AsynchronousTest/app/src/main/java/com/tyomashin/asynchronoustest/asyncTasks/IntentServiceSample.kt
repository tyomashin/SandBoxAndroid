package com.tyomashin.asynchronoustest.asyncTasks

import android.app.IntentService
import android.content.Intent
import android.support.v4.content.LocalBroadcastManager
import android.util.Log
import com.tyomashin.asynchronoustest.activities.MainActivity
import com.tyomashin.asynchronoustest.network.WebAPIManipulator

/*
* IntentService ... ワーカースレッドでバックグラウンドタスクを実行する。
*
* ・処理結果をメインスレッドに通知するには、ブロードキャストを使用する。
*　　：ここでは明示的インテントを使用する。
*　　：MainActivity でブロードキャストレシーバーを登録している。
*
* 参考：
* ・IntentServiceの例文
* 　https://woshidan.hatenablog.com/entry/2016/01/11/211856
*
* ・BroadcastReceiver の例文
* 　https://qiita.com/wakwak/items/eaaaa27881fa79dfc180
* */

class IntentServiceSample : IntentService("IntentServiceSample") {

    // 定数の定義
    companion object{
        const val ACTION_MAIN = "com.tyomashin.asynchronoustest.usecases.intentservicesample.action.ACTION_MAIN"
    }

    override fun onHandleIntent(intent: Intent?) {
        //処理
        if (intent?.action == ACTION_MAIN) {
            handleActionMain()
        }else{
            Log.d("IntentService", intent?.action)
        }
    }

    private fun handleActionMain(){
        Log.d("IntentService", "ActionMain")
        // ワーカースレッドで実行
        Log.d("CurrentThread", Thread.currentThread().name)
        Thread.sleep(5000)
        WebAPIManipulator.getHttpRespons("https://negichou.com/")
        Log.d("IntentService", "Finish")
        //アクティビティに通知
        /*
        * todo:
        * 　Activity にメッセージを送る際には、ブロードキャストレシーバーを使うしかない。
        * 　IntentServiceはインスタンス化できないため、コンストラクタにActivityを渡せないため。
        * */
        //明示的インテントをブロードキャスト
        val intent = Intent(applicationContext, BroadcastReceiverSample::class.java)
        intent.putExtra("flag", true)
        intent.action = MainActivity.ACTION_INTENT_SERVICE
        LocalBroadcastManager.getInstance(baseContext).sendBroadcast(intent)
    }

}
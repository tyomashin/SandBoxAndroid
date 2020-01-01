package com.tyomashin.asynchronoustest.activities

import android.content.Intent
import android.content.IntentFilter
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.LocalBroadcastManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import com.tyomashin.asynchronoustest.R
import com.tyomashin.asynchronoustest.entity.SampleAdapter
import com.tyomashin.asynchronoustest.entity.SampleData
import com.tyomashin.asynchronoustest.network.WebAPIManipulator
import com.tyomashin.asynchronoustest.asyncTasks.*

/*
* ・行をタップすると、10秒カウントが開始する。
* ・カウントは AsyncTask を使用している。
* ・複数行タップすると、ランナブルキューにタスクが積まれていき、シーケンシャルに処理される。
* 　並行して処理したかったら、自分でスレッドを作る必要がある？
* */

class MainActivity : AppCompatActivity() {

    // IntentService でバックグラウンド処理した結果を受信する BroadcastReceiver に設定するアクションフィルターの文字列
    companion object{
        const val ACTION_INTENT_SERVICE = "com.tyomashin.asynchronoustest.usecases.intentservicesample.ACTION_MAIN"
    }

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var myDataSet : ArrayList<SampleData>

    private lateinit var broadcastReceiverSample : BroadcastReceiverSample

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewManager = LinearLayoutManager(this)
        myDataSet = createDataSet()
        viewAdapter = SampleAdapter(
            myDataSet,
            object : SampleAdapter.IMyAdapterClick {
                override fun clickImageView() {
                    Log.d("imageView", "tap")
                }

                override fun clickRow(targetView: View) {
                    val targetPosition = recyclerView.getChildAdapterPosition(targetView)
                    AsyncTaskSample(viewAdapter, myDataSet, targetPosition).execute()
                }
            })
        recyclerView = findViewById<RecyclerView>(R.id.recyclerView).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }

        // IntentService から通知を受け取る BroadcastReceiver を登録する
        broadcastReceiverSample = BroadcastReceiverSample(this)
        val filter = IntentFilter()
        filter.addAction(ACTION_INTENT_SERVICE)
        LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiverSample, filter)

    }

    override fun onStart() {
        super.onStart()

        // サービスの実行

        val intent = Intent(this, IntentServiceSample::class.java)
        intent.action = IntentServiceSample.ACTION_MAIN
        startService(intent)


        // Retrofit2 による WebAPI コールの実行
        WebAPIManipulator.enqueueRetrofitAPISample()
    }

    override fun onDestroy() {
        super.onDestroy()
        LocalBroadcastManager.getInstance(this).unregisterReceiver(broadcastReceiverSample)
    }

    private fun createDataSet() : ArrayList<SampleData>{
        val tmpArray = ArrayList<SampleData>()

        for (i in 0 .. 3){
            var tmpData = SampleData(text = "text $i")
            tmpArray.add(tmpData)
        }
        return tmpArray
    }

    fun finishIntentServiceTask(flag : Boolean){
        Log.d("finishIntentServiceTask", flag.toString())
        myDataSet.add(SampleData())
        viewAdapter.notifyDataSetChanged()
    }

}

package com.tyomashin.workmanagersample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.tyomashin.workmanagersample.Geofence.SampleWorker
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button1.setOnClickListener {
            startWorkManager()
        }
    }
    // WorkManager を実行する
    private fun startWorkManager(){

        val taskUniqueId = "1"

        // 制約を作成
        //val constraints = Constraints.Builder()
        // タスクの作成
        val taskWorker = PeriodicWorkRequestBuilder<SampleWorker>(5, TimeUnit.SECONDS)
            .addTag(taskUniqueId)
            .build()
        // タスクをエンキュー
        val workManager = WorkManager.getInstance()
        //workManager.enqueue(taskWorker)
        workManager.enqueueUniquePeriodicWork(
            taskUniqueId,
            ExistingPeriodicWorkPolicy.REPLACE,
            taskWorker
        )
        // タスクのキャンセル
        //workManager.cancelWorkById(taskUniqueId)
        Log.d("startWork", "now")
    }
}

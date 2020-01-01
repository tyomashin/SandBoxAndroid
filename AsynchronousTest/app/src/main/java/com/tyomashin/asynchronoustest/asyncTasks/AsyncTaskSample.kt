package com.tyomashin.asynchronoustest.asyncTasks

import android.os.AsyncTask
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.tyomashin.asynchronoustest.entity.SampleData

/*
* AsyncTask のサンプルクラス
*
* ○AsyncTask<Params, Progress, Result> の特徴
*
* ・バックグラウンド処理、処理結果をUIスレッドに反映、を簡単に実現可能
* ・内部では別スレッドの作成などをしているが、それをラップしている
*
* 　公式：https://developer.android.com/reference/android/os/AsyncTask
*
* ○３種類のジェネリクス型
*
*  継承するときは意識する必要がある
*
*  ・Params：非同期処理の実行メソッド（doInBackground）に渡す型。
*  　　　　　これは、基本的にはUIスレッドが AsyncTaskSample().execute(Params) として渡す
*  ・Progress：処理途中に随時UIの更新を行うメソッド（onProgressUpdate）に渡す型
*  ・Result：処理が完了した後に呼び出すメソッド（onPostExecute）に渡す型
*
* */

class AsyncTaskSample(var targetAdapter : RecyclerView.Adapter<*>, var targetArrayList : ArrayList<SampleData>, var targetPosition : Int)
    : AsyncTask<Void, Int, Boolean>() {

    override fun onPreExecute() {
        Log.d("onPreExecute", "start")
    }

    /*
    * バックグラウンド処理を実行するメソッド。
    * この中でpublishProgress（Progress）を呼び出すことで、処理を随時UIスレッドに反映する
    * */
    override fun doInBackground(vararg params: Void?): Boolean {

        Log.d("doInBackground", "start")

        var count = 0

        while (true){
            if (count > 10) break
            Thread.sleep(1000)
            publishProgress(count)
            count++
        }
        return true
    }

    /*
    * UIスレッドで実行するメソッド
    * */
    override fun onProgressUpdate(vararg values: Int?) {
        targetArrayList[targetPosition].text = values[0].toString()
        targetAdapter.notifyDataSetChanged()
    }

    /*
    * 処理が完了した後に呼び出すメソッド
    * */
    override fun onPostExecute(result: Boolean) {
        Log.d("onPostExecute", "start")
        targetArrayList[targetPosition].text = "0"
    }

}
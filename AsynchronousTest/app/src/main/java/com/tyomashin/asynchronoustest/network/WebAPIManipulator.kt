package com.tyomashin.asynchronoustest.network

import android.util.Log
import com.tyomashin.asynchronoustest.retrofits.IApiService
import com.tyomashin.asynchronoustest.retrofits.RandomUserDemo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

/**
 * ネットワーク通信を行う処理をまとめたヘルパークラス
 *
 * @author tyomashin
 *         okazaki
 * @param なし
 */
class WebAPIManipulator {

    // static メソッド
    companion object {
        /**
         * Http 通信を行う。
         * HttpURLConnection を使用。
         * */
        fun getHttpRespons(url_str: String) {
            // HTTP コネクションを確立する準備
            var con: HttpURLConnection? = null
            var url = URL(url_str)
            try {
                // 指定URLに対するコネクションを確立する
                con = url.openConnection() as HttpURLConnection
                con.connect()
                // HTTPレスポンスコード
                if (con.responseCode == HttpURLConnection.HTTP_OK){
                    val br = BufferedReader(InputStreamReader(con.inputStream))
                    val sb = StringBuffer()
                    for (line in br.readLines()){
                        line?.let { sb.append(line) }
                    }
                    Log.d("httpResponse", sb.toString())
                    br.close()
                }
            }
            finally {
                con?.disconnect()
            }
        }

        /**
         * Retrofit2 を使ってWebAPIを呼び出す.
         * */
        fun enqueueRetrofitAPISample(){
            val service : IApiService? = IApiService.getInstance()
            val call = service?.apiDemo()
            Log.d("createApiIns", "now")
            // API コールを非同期で行う
            call?.enqueue(object : Callback<RandomUserDemo> {
                // 通信成功時
                override fun onResponse(call: Call<RandomUserDemo>, response: Response<RandomUserDemo>) {
                    if (response.isSuccessful) {
                        val sampleObject = response.body()
                        Log.d("sampleObject", sampleObject.toString())
                        // メインスレッドで実行
                        Log.d("CurrentThread", Thread.currentThread().name)
                    }else {
                        Log.d("Retrofit_test", "error_code" + response.code())
                    }
                }
                // 通信失敗時
                override fun onFailure(call: Call<RandomUserDemo>, t: Throwable) {
                    Log.d("onFailure", t.localizedMessage)
                }
            })
        }

    }

}
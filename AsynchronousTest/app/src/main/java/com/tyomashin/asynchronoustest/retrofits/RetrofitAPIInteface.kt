package com.tyomashin.asynchronoustest.retrofits

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers

/**
 * Retrofit で使用する API と、その実装を返すシングルトンの定義.
 *
 * Retrofit では API をインタフェースで表現する.
 * 　GET, POST などのリクエスト,
 * 　およびレスポンスのレシーバクラスを定義する.
 * Retrofit インスタンスを介して API インタフェースの実装インスタンスを取得している.
 * これは初期化コストが高いため、シングルトンで一度だけ生成している.
 *
 * */

interface IApiService {
    //@Headers("Hoge: aaa")
    @GET("api")
    fun apiDemo(): Call<RandomUserDemo>

    // 複数のAPIを定義可能
    @GET("hoge")
    fun aaa(): Call<RandomUserDemo>

    // 参考：https://gist.github.com/rubenpla-develop/343d53d717f223275317e9aded01066f
    companion object{
        // キャッシュではなくメインメモリを必ず参照するように指定
        @Volatile
        private var retrofit : Retrofit? = null
        private const val BASE_URL : String = "https://randomuser.me/"

        // Volatile と似たことが実現できる、ブロックレベルアノテーション
        @Synchronized
        fun getInstance() : IApiService? {
            retrofit ?: synchronized(this){
                retrofit = buildRetrofit()
            }
            return retrofit?.create(IApiService::class.java)
        }

        private fun buildRetrofit() : Retrofit{

            // HttpClient セットアップ
            val interceptor = Interceptor{chain ->

                val original = chain.request()
                // ヘッダの設定
                val request = original.newBuilder()
                    .header("Accept", "application/json")
                    .header("Auth", "111")
                    .build()

                return@Interceptor chain.proceed(request)
            }
            // HTTPログ出力の設定
            val loggingInterceptor = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.HEADERS
            }
            // 先にリクエストインタセプタを追加する必要がある。
            // 参考：https://stackoverflow.com/questions/35265906/retrofit-2-x-log-header-for-request-and-response
            val client = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(loggingInterceptor)
                .build()

            return retrofit2.Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }

}
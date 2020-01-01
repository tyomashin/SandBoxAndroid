package com.tyomashin.asynchronoustest.retrofits

/**
 * Retrofit で受信するオブジェクト一覧.
 *
 * Retrofit で受け取る JSON データを以下のデータオブジェクトに変換する.
 * Json の key と、オブジェクトの変数名が一致するようにすること.
 * Custom Key Name (key と変数名を変えたい）場合は、SerializedName アノテーションをつける.
 *
 * */
data class RandomUserDemo (
    var results: List<Result>,
    var info: Info
)

data class Result (
    var gender: String,
    var email: String,
    var phone: String,
    var cell: String)

data class Info (
    var seed: String,
    var results: Int,
    var page: Int,
    var version: Float)
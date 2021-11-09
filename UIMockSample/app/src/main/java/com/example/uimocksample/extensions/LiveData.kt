package com.example.uimocksample.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.util.concurrent.atomic.AtomicBoolean

/**
 * 初期値は無視するLiveDataを生成。
 * 主に画面遷移のLiveDataで使用する
 * 参考：
 * https://satoshun.github.io/2018/05/singlelivedata/
 */
fun <T> singleLiveData(): MutableLiveData<T> {
    // skip用の初期値を入れておく
    return MutableLiveData<T>().also { it.value = null }
}

/**
 * 初期値を無視して購読する。
 * 主に画面遷移のLiveDataで使用する
 */
fun <T> LiveData<T>.observeSingle(owner: LifecycleOwner, observer: ((T?) -> Unit)) {
    // 最初の値は常にskipすることで、キャッシュを無視する
    val firstIgnore = AtomicBoolean(true)
    this.observe(owner, Observer {
        if (firstIgnore.getAndSet(false)) return@Observer
        observer(it)
    })
}
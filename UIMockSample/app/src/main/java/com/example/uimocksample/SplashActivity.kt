package com.example.uimocksample

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.uimocksample.databinding.ActivitySplashBinding
import com.example.uimocksample.viewmodel.SplashViewModel

class SplashActivity: AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivitySplashBinding
    private val viewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ヘッダーを非表示にする
        supportActionBar?.hide()

        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navController = findNavController(R.id.nav_host_fragment_content_splash)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        // 画面遷移要求を購読
        /**
         * 簡易表記
        viewModel.transitionMode.observe(this, Observer { transitionMode ->
        Log.d("hoge!!", transitionMode.toString())
        })
         */
        val transitionObserver = Observer<SplashViewModel.SplashTransitionMode?>{ transitionMode ->
            Log.d("hoge!", transitionMode.toString())
            when (transitionMode){
                SplashViewModel.SplashTransitionMode.UIList -> {
                    // UIリスト画面に遷移する
                    // memo: 第二引数に、遷移先へのデータを指定することもできる
                    // https://qiita.com/outerlet/items/4d2a20e0049f9d023cfb
                    findNavController(R.id.nav_host_fragment_content_splash).navigate(R.id.action_Splash_to_Main)
                    finish()
                }
            }
        }
        viewModel.transitionMode.observe(this, transitionObserver)

        // スプラッシュ画面表示を通知
        viewModel.startActivity()
    }
}
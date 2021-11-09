package com.example.uimocksample.fragment.splash

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.uimocksample.R
import com.example.uimocksample.databinding.FragmentSplashBinding
import com.example.uimocksample.databinding.FragmentUilistBinding
import com.example.uimocksample.viewmodel.SplashViewModel
import com.example.uimocksample.viewmodel.UIComponentAdapter
import com.example.uimocksample.viewmodel.UIListViewModel

class SplashFragment: Fragment() {
    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!
    val viewModel: SplashViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
                    findNavController().navigate(R.id.action_Splash_to_UIList)
                }
            }
        }
        viewModel.transitionMode.observe(viewLifecycleOwner, transitionObserver)

        // スプラッシュ画面表示を通知
        viewModel.startActivity()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
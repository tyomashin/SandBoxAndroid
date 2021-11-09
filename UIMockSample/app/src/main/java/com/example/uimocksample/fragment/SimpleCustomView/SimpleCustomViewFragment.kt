package com.example.uimocksample.fragment.SimpleCustomViewFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.uimocksample.R
import com.example.uimocksample.databinding.FragmentSimpleCustomViewBinding
import com.example.uimocksample.databinding.FragmentSplashBinding

/**
 * 単純なカスタムViewを並べているフラグメント
 */
class SimpleCustomViewFragment : Fragment(){
    private var _binding: FragmentSimpleCustomViewBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSimpleCustomViewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.simpleCircleView.setCircleBackgroundColor(bigCircleColor = R.color.red, smallCircleColor = R.color.blue)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
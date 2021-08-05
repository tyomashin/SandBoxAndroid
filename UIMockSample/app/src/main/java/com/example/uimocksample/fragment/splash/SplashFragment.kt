package com.example.uimocksample.fragment.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.uimocksample.databinding.FragmentSplashBinding
import com.example.uimocksample.databinding.FragmentUilistBinding
import com.example.uimocksample.viewmodel.UIComponentAdapter
import com.example.uimocksample.viewmodel.UIListViewModel

class SplashFragment: Fragment() {
    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!
    // val viewModel: UIListViewModel by viewModels()
    // private lateinit var componentListAdapter: UIComponentAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
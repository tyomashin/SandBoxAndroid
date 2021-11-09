package com.example.uimocksample.fragment.BottomTab

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.uimocksample.MainActivity
import com.example.uimocksample.R
import com.example.uimocksample.databinding.FragmentBottomTab1Binding
import com.google.android.material.bottomnavigation.BottomNavigationView

class BottomTabFragment: Fragment() {
    private var _binding: FragmentBottomTab1Binding? = null
    private val binding get() = _binding!!
    // val viewModel: UIListViewModel by viewModels()
    // private lateinit var componentListAdapter: UIComponentAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBottomTab1Binding.inflate(inflater, container, false)
        return binding.root
        //return null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navView: BottomNavigationView = binding.navView
        val navController = findNavController()
        Log.d("hoge!", navController.toString())

        val hoge = NavHostFragment.findNavController(this)
//
//        binding.button.setOnClickListener{
//            // findNavController().navigate(R.id.SampleFragment)
//            findNavController().navigate(R.id.HomeFragment)
//        }

        /*
        // memo: ここで呼ぶと、まだActivityの初期化が完了していないとのことでエラーになる
        // memo: onActivityCreatedで実行すると問題ないが、非推奨メソッドで呼んでいるので場所を考える
        val sampleActivity = activity as SampleActivity
        val navViewNavigation = Navigation.findNavController(sampleActivity, R.id.nav_host_fragment_fragment_sample)
        navView.setupWithNavController(navViewNavigation)
        */

        // setupActionBarWithNavController(navController, appBarConfiguration)
        // navView.setupWithNavController(hoge)
        // NavigationUI.setupWithNavController(navView, navController)

        //NavigationUI.setupWithNavController()
    }

    // TODO:
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val navView: BottomNavigationView = binding.navView
        val sampleActivity = activity as MainActivity
        val navViewNavigation = Navigation.findNavController(sampleActivity, R.id.nav_host_fragment)

        navView.setupWithNavController(navViewNavigation)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
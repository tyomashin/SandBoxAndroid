package com.example.uimocksample.fragment.UIList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.*
import com.example.uimocksample.MainActivity
import com.example.uimocksample.MyApplication
import com.example.uimocksample.SplashActivity
import com.example.uimocksample.databinding.FragmentUilistBinding
import com.example.uimocksample.databinding.UicomponentListItemBinding
import com.example.uimocksample.entity.UiComponentData
import com.example.uimocksample.viewmodel.UIComponentAdapter
import com.example.uimocksample.viewmodel.UIListViewModel

/**
 * UIコンポーネントへ遷移するリスト画面
 */
class UiListFragment: Fragment() {
    private var _binding: FragmentUilistBinding? = null
    private val binding get() = _binding!!
    val viewModel: UIListViewModel by viewModels()
    private lateinit var componentListAdapter: UIComponentAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUilistBinding.inflate(inflater, container, false)
            .apply {
                uiList.run{
                    layoutManager = LinearLayoutManager(context)
                    /*
                    // 区切り線を表示する
                    addItemDecoration(
                        DividerItemDecoration(
                            context,
                            DividerItemDecoration.VERTICAL
                        )
                    )
                    */

                    adapter = UIComponentAdapter(viewLifecycleOwner, this@UiListFragment.viewModel).also {
                        componentListAdapter = it
                    }

                }
            }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.run {
            componentListAdapter.submitList(getComponentList(context))

            /*
            getComponentList.observe(viewLifecycleOwner, {
                componentListAdapter.submitList(it)
            })
            */
        }

        // (activity as? MainActivity)?.supportActionBar?.hide()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
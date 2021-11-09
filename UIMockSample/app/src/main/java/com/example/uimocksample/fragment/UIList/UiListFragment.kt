package com.example.uimocksample.fragment.UIList

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.*
import com.example.uimocksample.MainActivity
import com.example.uimocksample.R
import com.example.uimocksample.databinding.FragmentUilistBinding
import com.example.uimocksample.entity.UIListTransitionMode
import com.example.uimocksample.extensions.observeSingle
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

        // 画面遷移要求を購読
        // memo: 遷移先から戻ってきたときに、繰り返し画面遷移が走らないようにするために「observeSingle」を使用
        viewModel.transitionMode.observeSingle(owner = viewLifecycleOwner){
            // String Template
            Log.d("hoge!!!!", "$it")
            when(it){
                UIListTransitionMode.SimpleCustomView -> {
                    Log.d("hoge!!!", "transition simple custom view")
                    // 画面遷移する
                    findNavController().navigate(R.id.action_UIList_to_SimpleCustomViewFragment)
                }
                UIListTransitionMode.BottomTabWithFragmentView -> {
                    findNavController().navigate(R.id.action_UIList_to_BottomTabFragment)
                }
                else -> {}
            }
        }

        // ツールバーを表示して、戻るボタンを非表示にする
        (activity as? MainActivity)?.supportActionBar?.let{
            it.show()
            it.setDisplayHomeAsUpEnabled(false)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
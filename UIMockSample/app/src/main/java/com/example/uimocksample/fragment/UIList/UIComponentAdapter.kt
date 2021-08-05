package com.example.uimocksample.viewmodel

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.uimocksample.R
import com.example.uimocksample.databinding.UicomponentListItemBinding
import com.example.uimocksample.entity.UiComponentData
import com.example.uimocksample.utils.SizeConverter


/**
 * UIコンポーネントリストに表示する情報を作成するアダプター
 */
class UIComponentAdapter(
    private val viewLifecycleOwner: LifecycleOwner,
    private val viewModel: UIListViewModel
): ListAdapter<UiComponentData, UIComponentAdapter.UIComponentViewHolder>(DiffCallback) {

    // ViewHolder の役割をしっかり確認する
    class UIComponentViewHolder(private val binding: UicomponentListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: UiComponentData, viewLifecycleOwner: LifecycleOwner, viewModel: UIListViewModel) {
            binding.run {
                Log.d("hoge, height", root.layoutParams.height.toString())
                text.text = item.name
                imageview.setImageResource(R.mipmap.ic_launcher)

                // viewLifecycleOwner.conte
                //lifecycleOwner = viewLifecycleOwner
                //user = item
                //this.viewModel = viewModel

                // executePendingBindings()
            }
        }

        // 任意のメソッドを定義できる
        fun hoge(){
            Log.d("hoge", "hoge")
        }
    }

    // ViewHolderを生成する
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UIComponentViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return UIComponentViewHolder(UicomponentListItemBinding.inflate(layoutInflater, parent, false))
    }

    // ViewHolderにデータをセットする
    override fun onBindViewHolder(holder: UIComponentViewHolder, position: Int) {
        // View の高さを可変に変更できる
        // holder.itemView.layoutParams.height = 200 * (position + 1)
        // memo: レイアウトに指定する値はピクセルのため、DPを変換している
        val heightDp = 100
        var heightPixel = SizeConverter.convertDpToPixel(heightDp)
        holder.itemView.layoutParams.height = heightPixel

        // ViewHolder にデータをセット
        holder.bind(getItem(position), viewLifecycleOwner, viewModel)
        holder.itemView.setOnClickListener{
            viewModel.tapComponentItem(position)
        }

        // memo: ViewHolder のメソッドも呼べる
        holder.hoge()
    }
}

private object DiffCallback : DiffUtil.ItemCallback<UiComponentData>() {
    override fun areItemsTheSame(oldItem: UiComponentData, newItem: UiComponentData): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: UiComponentData, newItem: UiComponentData): Boolean {
        return oldItem == newItem
    }

}
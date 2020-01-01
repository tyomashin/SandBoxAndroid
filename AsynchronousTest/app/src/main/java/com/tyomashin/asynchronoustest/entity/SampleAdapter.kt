package com.tyomashin.asynchronoustest.entity

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.tyomashin.asynchronoustest.R

/*
* Adapter ... RecyclerView に渡すデータセットを定義
* ViewHolder ... データセットのうち１行ごとのデータを定義
* */

class SampleAdapter(var dataSet : ArrayList<SampleData>, l : IMyAdapterClick) : RecyclerView.Adapter<SampleAdapter.SampleViewHolder>(){

    var adapterListener : IMyAdapterClick = l

    interface IMyAdapterClick{
        fun clickImageView()
        fun clickRow(v : View)
    }

    class SampleViewHolder(v : View) : RecyclerView.ViewHolder(v){

        var imageView : ImageView = v.findViewById(R.id.imageView)
        var textView : TextView = v.findViewById(R.id.textView)

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType : Int): SampleViewHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.recyclerview_layout, viewGroup,false)
        return SampleViewHolder(v)
    }

    override fun onBindViewHolder(holder: SampleViewHolder, position: Int) {
        holder.imageView.setImageResource(R.mipmap.ic_launcher)
        holder.textView.text = dataSet[position].text

        holder.imageView.setOnClickListener {
            adapterListener.clickImageView()
        }
        holder.itemView.setOnClickListener {
            adapterListener.clickRow(holder.itemView)
        }
    }

    override fun getItemCount(): Int = dataSet.size

}
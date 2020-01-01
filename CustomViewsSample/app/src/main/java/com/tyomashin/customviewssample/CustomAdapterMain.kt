package com.tyomashin.customviewssample

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class CustomAdapterMain(var dataList : ArrayList<CustomDataMain>, var activity: MainActivity)
    : RecyclerView.Adapter<CustomAdapterMain.SampleViewHolderMain>(){

    class SampleViewHolderMain(v : View) : RecyclerView.ViewHolder(v){
        var title : TextView = v.findViewById(R.id.textView)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): SampleViewHolderMain {
        val tmpView = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.sample_view_holder_main, viewGroup, false)
        return SampleViewHolderMain(tmpView)
    }

    override fun onBindViewHolder(holder: SampleViewHolderMain, position: Int) {
        holder.title.setText(dataList[position].titleId)
        // clickイベントを登録
        holder.itemView.setOnClickListener {
            activity.clickListItem(dataList[holder.adapterPosition])
        }
    }

}
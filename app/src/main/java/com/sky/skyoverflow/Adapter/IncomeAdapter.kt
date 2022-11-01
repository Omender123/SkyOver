package com.sky.skyoverflow.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sky.skyoverflow.databinding.CustomIncomeLayoutBinding

class IncomeAdapter : RecyclerView.Adapter<IncomeAdapter.MyViewHolder> {
    private lateinit var binding: CustomIncomeLayoutBinding
    private lateinit var mContext: Context
    private lateinit var data: ArrayList<String>

    constructor(mContext: Context, data: ArrayList<String>) {
        this.mContext = mContext
        this.data = data
    }

    constructor()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        binding = CustomIncomeLayoutBinding.inflate(inflater, parent, false)

        return MyViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        binding.txt.text = data[position]
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}

}
package com.sky.skyoverflow.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sky.skyoverflow.databinding.CustomCartLayoutBinding

class CartAdapter : RecyclerView.Adapter<CartAdapter.MyViewHolder> {
    private lateinit var binding: CustomCartLayoutBinding
    private var list: ArrayList<String>
    private var mContext: Context

    constructor(list: ArrayList<String>, mContext: Context) {
        this.list = list
        this.mContext = mContext
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        binding = CustomCartLayoutBinding.inflate(inflater, parent, false)
        return MyViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        binding.txtMrp.text = "MRP  ₹ " + list[position]
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    }

}
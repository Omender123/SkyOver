package com.sky.skyoverflow.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sky.skyoverflow.databinding.CustomOrderHistoryLayoutBinding

class OrderHistoryAdapter  :RecyclerView.Adapter<OrderHistoryAdapter.MyViewHolder>{
    private lateinit var binding: CustomOrderHistoryLayoutBinding
    private var mContext: Context
    private var data :ArrayList<Boolean>

    constructor(mContext: Context, data: ArrayList<Boolean>) {
        this.mContext = mContext
        this.data = data
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
      val inflater = LayoutInflater.from(parent.context)
        binding = CustomOrderHistoryLayoutBinding.inflate(inflater,parent,false)

        return MyViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        if (data[position]){
            binding.linear.visibility = View.GONE
            binding.txtDate.visibility = View.GONE
            binding.txtSuccess.visibility = View.VISIBLE
        }else{
            binding.linear.visibility = View.VISIBLE
            binding.txtDate.visibility = View.VISIBLE
            binding.txtSuccess.visibility = View.GONE
        }
    }

    override fun getItemCount(): Int {
      return data.size
    }


    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}
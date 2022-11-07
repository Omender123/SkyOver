package com.sky.skyoverflow.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sky.skyoverflow.Model.Response.ReHistoryResponse
import com.sky.skyoverflow.R
import com.sky.skyoverflow.databinding.CustomRehistoryLayoutBinding

class ReHistoryAdapter : RecyclerView.Adapter<ReHistoryAdapter.MyViewHolder> {
    private lateinit var binding: CustomRehistoryLayoutBinding
    private var data: ReHistoryResponse
    private var mContext: Context

    constructor(data: ReHistoryResponse, mContext: Context) {
        this.data = data
        this.mContext = mContext
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = CustomRehistoryLayoutBinding.inflate(inflater, parent, false)

        return MyViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        binding.txt.text = data.Data[position].sOtype
        binding.txtDate.text = data.Data[position].sRDate
        binding.txtAmount.text =
            mContext.resources.getString(R.string.rupee_sign) + "" + data.Data[position].amount
        binding.txtType.text = data.Data[position].sOperatorName
        binding.txtStatus.text = data.Data[position].sStatus
        if (data.Data[position].sStatus.equals("Success", true)) {
            binding.txtStatus.setTextColor(mContext.resources.getColor(R.color.green))
        } else {
            binding.txtStatus.setTextColor(mContext.resources.getColor(R.color.red))
        }
    }

    override fun getItemCount(): Int {
        return data.Data.size
    }


    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}
}
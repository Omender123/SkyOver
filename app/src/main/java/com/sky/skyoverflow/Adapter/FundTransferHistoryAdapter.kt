package com.sky.skyoverflow.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sky.skyoverflow.Model.Response.AddFundHistoryResponse
import com.sky.skyoverflow.Model.Response.FundTransactionResponse
import com.sky.skyoverflow.R
import com.sky.skyoverflow.databinding.CustomFundHostoryLayoutBinding
import com.sky.skyoverflow.databinding.CustomRehistoryLayoutBinding

class FundTransferHistoryAdapter : RecyclerView.Adapter<FundTransferHistoryAdapter.MyViewHolder> {
    private lateinit var binding: CustomFundHostoryLayoutBinding
    private var fundTransactionResponse: FundTransactionResponse
    private var mContext: Context

    constructor(fundTransactionResponse: FundTransactionResponse, mContext: Context) {
        this.fundTransactionResponse = fundTransactionResponse
        this.mContext = mContext
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = CustomFundHostoryLayoutBinding.inflate(inflater, parent, false)

        return MyViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.setIsRecyclable(true)
        binding.TransactionDate.text = fundTransactionResponse.Data[position].TransactionDate
        binding.transtype.text = fundTransactionResponse.Data[position].transtype

        if (fundTransactionResponse.Data[position].debit!=null){
            binding.amount.text =
                mContext.resources.getString(R.string.rupee_sign) + "" + fundTransactionResponse.Data[position].debit
            binding.status.text = "Credit"
            binding.status.setTextColor(mContext.resources.getColor(R.color.green))
        }else{
            binding.amount.text =
                mContext.resources.getString(R.string.rupee_sign) + "" + fundTransactionResponse.Data[position].credit
            binding.status.text = "Debit"
            binding.status.setTextColor(mContext.resources.getColor(R.color.red))
        }
        binding.amount.text =
            mContext.resources.getString(R.string.rupee_sign) + "" + fundTransactionResponse.Data[position].debit
        binding.typepay.text = ""

        binding.reamrk.text =fundTransactionResponse.Data[position].reamrk

    }

    override fun getItemCount(): Int {
        return fundTransactionResponse.Data.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}
}
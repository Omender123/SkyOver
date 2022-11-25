package com.sky.skyoverflow.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sky.skyoverflow.Model.Response.AddFundHistoryResponse
import com.sky.skyoverflow.R
import com.sky.skyoverflow.databinding.CustomFundHostoryLayoutBinding
import com.sky.skyoverflow.databinding.CustomRehistoryLayoutBinding

class AddFundHistoryAdapter : RecyclerView.Adapter<AddFundHistoryAdapter.MyViewHolder> {
    private lateinit var binding: CustomFundHostoryLayoutBinding
    private var addFundHistoryResponse: AddFundHistoryResponse
    private var mContext: Context

    constructor(addFundHistoryResponse: AddFundHistoryResponse, mContext: Context) {
        this.addFundHistoryResponse = addFundHistoryResponse
        this.mContext = mContext
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = CustomFundHostoryLayoutBinding.inflate(inflater, parent, false)

        return MyViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.setIsRecyclable(true)
        binding.TransactionDate.text = addFundHistoryResponse.Data[position].PaymentDate
        binding.transtype.text = addFundHistoryResponse.Data[position].walletType
        binding.amount.text =
            mContext.resources.getString(R.string.rupee_sign) + "" + addFundHistoryResponse.Data[position].Ramount
        binding.typepay.text = addFundHistoryResponse.Data[position].PaymentMode

        binding.status.text = addFundHistoryResponse.Data[position].RfStatus
        if (addFundHistoryResponse.Data[position].RfStatus.equals("Approved", true)) {
            binding.status.setTextColor(mContext.resources.getColor(R.color.green))
        } else {
            binding.status.setTextColor(mContext.resources.getColor(R.color.red))
        }

        binding.reamrk.text =addFundHistoryResponse.Data[position].Commentbox

    }

    override fun getItemCount(): Int {
        return addFundHistoryResponse.Data.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}
}
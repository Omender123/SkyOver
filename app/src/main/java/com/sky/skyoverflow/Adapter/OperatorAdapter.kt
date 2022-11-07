package com.sky.skyoverflow.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.sky.skyoverflow.Model.Response.Operator
import com.sky.skyoverflow.Model.Response.OperatorResponse
import com.sky.skyoverflow.databinding.CustomCartLayoutBinding
import com.sky.skyoverflow.databinding.CustomSpinnerLayoutBinding
import kotlinx.coroutines.NonDisposableHandle.parent

class OperatorAdapter : BaseAdapter {
    private lateinit var binding: CustomSpinnerLayoutBinding
    private var mContext: Context
    private var data: ArrayList<Operator>

    constructor(mContext: Context, data: ArrayList<Operator>) : super() {
        this.mContext = mContext
        this.data = data
    }


    override fun getCount(): Int {
        return data.size
    }

    override fun getItem(p0: Int): Any {

        return data[p0].OpCode
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val inflater = LayoutInflater.from(p2?.context)

        binding = CustomSpinnerLayoutBinding.inflate(inflater, p2, false)

        binding.txt.text = data[p0].OperatorName
        return binding.root
    }
}
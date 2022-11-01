package com.sky.skyoverflow.Fragment.Cart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sky.skyoverflow.Adapter.AddressAdapter
import com.sky.skyoverflow.Adapter.OrderHistoryAdapter
import com.sky.skyoverflow.R
import com.sky.skyoverflow.databinding.FragmentOrderHistoryBinding


class OrderHistoryFragment : Fragment() {
    private lateinit var binding: FragmentOrderHistoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentOrderHistoryBinding.inflate(inflater, container, false)
        setData()
        return binding.root
    }

    private fun setData() {
        var data: ArrayList<Boolean> = ArrayList()
        data.clear()
        data.add(false)
        data.add(true)
        data.add(false)
        data.add(true)


        val mLayoutManager1: RecyclerView.LayoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.recyclerView.setLayoutManager(mLayoutManager1)
        binding.recyclerView.setItemAnimator(DefaultItemAnimator())
        var adapter =
            OrderHistoryAdapter(requireContext(), data);
        binding.recyclerView.setAdapter(adapter)
    }

}
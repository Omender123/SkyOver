package com.sky.skyoverflow.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sky.skyoverflow.Adapter.ProductListAdapter
import com.sky.skyoverflow.R
import com.sky.skyoverflow.databinding.FragmentRepurchaseBinding

class RepurchaseFragment : Fragment() {
    private lateinit var binding: FragmentRepurchaseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRepurchaseBinding.inflate(inflater, container, false)
        setData()
        return binding.root
    }

    private fun setData() {
        var data: ArrayList<String> = ArrayList<String>()
        data.clear()
        data.add("1299")
        data.add("2999")
        data.add("99")
        data.add("599")
        data.add("99")
        data.add("999")


        val mLayoutManager1: RecyclerView.LayoutManager =
            GridLayoutManager(context, 2, RecyclerView.VERTICAL, false)
        binding.recyclerView.setLayoutManager(mLayoutManager1)
        binding.recyclerView.setItemAnimator(DefaultItemAnimator())
        var adapter =
            ProductListAdapter(data, requireContext());
        binding.recyclerView.setAdapter(adapter)
    }

}
package com.sky.skyoverflow.Fragment.Dashboard

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sky.skyoverflow.Adapter.IncomeAdapter
import com.sky.skyoverflow.databinding.FragmentIncomeBinding


class IncomeFragment : Fragment() {
    private lateinit var binding: FragmentIncomeBinding
    val args: IncomeFragmentArgs by navArgs()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentIncomeBinding.inflate(inflater, container, false)

        if (args != null) {
            Log.e("level", args.title)
        }


        setData()

        return binding.root
    }

    private fun setData() {
        var data = ArrayList<String>()
        data.clear()
        data.add("Deposit Amount")
        data.add("Deposit Amount")
        data.add("Deposit Amount")
        data.add("Deposit Amount")
        data.add("Deposit Amount")
        data.add("Deposit Amount")
        data.add("Deposit Amount")
        data.add("Deposit Amount")
        data.add("Deposit Amount")
        data.add("Deposit Amount")
        data.add("Deposit Amount")


        val mLayoutManager1: RecyclerView.LayoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.recyclerView.setLayoutManager(mLayoutManager1)
        binding.recyclerView.setItemAnimator(DefaultItemAnimator())
        var adapter =
            IncomeAdapter(requireContext(), data);
        binding.recyclerView.setAdapter(adapter)

    }


}
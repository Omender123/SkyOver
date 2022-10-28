package com.sky.skyoverflow.Fragment.Cart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sky.skyoverflow.Adapter.CartAdapter
import com.sky.skyoverflow.Adapter.ProductListAdapter
import com.sky.skyoverflow.R
import com.sky.skyoverflow.databinding.FragmentYourCartBinding


class YourCartFragment : Fragment(), View.OnClickListener {
    private lateinit var binding: FragmentYourCartBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentYourCartBinding.inflate(inflater, container, false)
        binding.txtCheckout.setOnClickListener(this)
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
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.recyclerView.setLayoutManager(mLayoutManager1)
        binding.recyclerView.setItemAnimator(DefaultItemAnimator())
        var adapter =
            CartAdapter(data, requireContext());
        binding.recyclerView.setAdapter(adapter)
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {

            R.id.txt_checkout -> {
                findNavController().navigate(R.id.action_yourCartFragment_to_shippingAddressFragment)
            }
        }
    }
}
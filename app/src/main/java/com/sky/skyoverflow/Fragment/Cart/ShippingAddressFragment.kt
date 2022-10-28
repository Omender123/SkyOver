package com.sky.skyoverflow.Fragment.Cart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sky.skyoverflow.Adapter.AddressAdapter
import com.sky.skyoverflow.R
import com.sky.skyoverflow.databinding.FragmentShippingAddressBinding


class ShippingAddressFragment : Fragment(), View.OnClickListener {
    private lateinit var binding:FragmentShippingAddressBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =  FragmentShippingAddressBinding.inflate(inflater, container, false)
        binding.btnAddNewAddress.setOnClickListener(this)
        binding.btnContinue.setOnClickListener(this)
        setData()
        return  binding.root
    }
    private fun setData() {
        var data: ArrayList<String> = ArrayList()
        data.clear()
        data.add("Home")
        data.add("Home")
        data.add("Office")
        data.add("Office")


        val mLayoutManager1: RecyclerView.LayoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.recyclerviewDeliveryAddressList.setLayoutManager(mLayoutManager1)
        binding.recyclerviewDeliveryAddressList.setItemAnimator(DefaultItemAnimator())
        var adapter =
            AddressAdapter(requireContext(), data);
        binding.recyclerviewDeliveryAddressList.setAdapter(adapter)
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.btn_add_new_address ->{
                findNavController().navigate(R.id.action_shippingAddressFragment_to_deliveryAddressFragment)
            }

            R.id.btn_continue ->{
                findNavController().navigate(R.id.action_shippingAddressFragment_to_paymentModeFragment)
            }
        }
    }

}
package com.sky.skyoverflow.Fragment.Cart

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.sky.skyoverflow.R
import com.sky.skyoverflow.databinding.FragmentDeliveryAddressBinding


class DeliveryAddressFragment : Fragment(), AdapterView.OnItemSelectedListener {
    private lateinit var binding: FragmentDeliveryAddressBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDeliveryAddressBinding.inflate(inflater, container, false)
        val adapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.address_type,
            R.layout.custom_spinner_layout
        )
        binding.appcompactSpinner.adapter = adapter

        binding.appcompactSpinner.onItemSelectedListener = this

        return binding.root
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        (p0?.getChildAt(0) as TextView).gravity = Gravity.CENTER
        var address = p0?.getItemAtPosition(p2).toString()
        Log.e("walletType", address!!)
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

}
package com.sky.skyoverflow.Fragment.Dashboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sky.skyoverflow.R
import com.sky.skyoverflow.SharedPerfence.MyPreferences
import com.sky.skyoverflow.SharedPerfence.PrefConf
import com.sky.skyoverflow.databinding.FragmentTransferFundBinding
import com.sky.skyoverflow.databinding.FragmentWalletBinding


class Transfer_Fund_Fragment : Fragment() {
    private lateinit var binding: FragmentTransferFundBinding
    private var mainBal: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTransferFundBinding.inflate(inflater, container, false)
        mainBal =  resources.getString(R.string.rupee_sign) +MyPreferences.getInstance(requireContext()).getString(PrefConf.USER_MAINWALLETBALANCE,"0")
        binding.txtMainBal.text = mainBal


        return binding.root
    }

}
package com.sky.skyoverflow.Fragment.Dashboard

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sky.skyoverflow.Model.Response.Dashborad
import com.sky.skyoverflow.R
import com.sky.skyoverflow.databinding.FragmentWalletBinding


class WalletFragment : Fragment() {
    private lateinit var binding: FragmentWalletBinding
    var dashborad: Dashborad? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentWalletBinding.inflate(inflater, container, false)
        dashborad = arguments?.getSerializable("Dashborad") as Dashborad
        Log.e("dashboradResponse", "shshsh" + dashborad)

        binding.txtMainBal.text =
            resources.getString(R.string.rupee_sign) + dashborad?.MainwalletBallance
        binding.txtTopBal.text =
            resources.getString(R.string.rupee_sign) + dashborad?.topupWalletBalance
        binding.txtCardBal.text = resources.getString(R.string.rupee_sign) + dashborad?.CardWallet
        binding.txtSelfBal.text =
            resources.getString(R.string.rupee_sign) + dashborad?.slefCashBackWallet


        return binding.root
    }
}
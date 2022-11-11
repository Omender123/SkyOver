package com.sky.skyoverflow.Fragment.SlideMenu

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.sky.skyoverflow.Model.Response.ProfileDetails
import com.sky.skyoverflow.R
import com.sky.skyoverflow.SharedPerfence.MyPreferences
import com.sky.skyoverflow.SharedPerfence.PrefConf
import com.sky.skyoverflow.databinding.FragmentAccountSettingBinding


class AccountSettingFragment : Fragment(), View.OnClickListener {
    private lateinit var binding: FragmentAccountSettingBinding
    private var profileDetails: ProfileDetails? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAccountSettingBinding.inflate(inflater, container, false)

        profileDetails = MyPreferences.getInstance(requireContext())
            .getMemberDetails(PrefConf.PROFILE_DETAILS, null)

        Log.e("profileDetails", profileDetails.toString())

        if (profileDetails != null) {
            binding.txtBankNo.setText(profileDetails?.accountNo ?: "")

        }
        binding.lytPass.setOnClickListener(this)
        binding.lytBank.setOnClickListener(this)
        binding.lytKyc.setOnClickListener(this)
        return binding.root
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.lyt_pass -> {
                findNavController().navigate(R.id.action_accountSettingFragment_to_changePasswordFragment)
            }

            R.id.lyt_kyc -> {
                findNavController().navigate(R.id.action_accountSettingFragment_to_kycVerificationFragment)
            }

            R.id.lyt_bank -> {
                findNavController().navigate(R.id.action_accountSettingFragment_to_addBankFragment)
            }
        }
    }


}
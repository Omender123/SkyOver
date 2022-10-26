package com.sky.skyoverflow.Fragment.Dashboard

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.RadioGroup
import android.widget.Spinner
import androidx.navigation.fragment.findNavController
import com.sky.skyoverflow.R
import com.sky.skyoverflow.SharedPerfence.MyPreferences
import com.sky.skyoverflow.SharedPerfence.PrefConf
import com.sky.skyoverflow.databinding.FragmentMembeActivitionBinding
import com.sky.skyoverflow.databinding.FragmentMobileRechargeBinding


class MembeActivitionFragment : Fragment(), RadioGroup.OnCheckedChangeListener,
    View.OnClickListener {
    private lateinit var binding: FragmentMembeActivitionBinding
    private var mainBal: String? = null
    private var packageId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMembeActivitionBinding.inflate(inflater, container, false)
        mainBal =
            resources.getString(R.string.rupee_sign) + MyPreferences.getInstance(requireContext())
                .getString(
                    PrefConf.USER_MAINWALLETBALANCE, "0"
                )
        binding.txtMainBal.text = mainBal

        binding.rgPackage.setOnCheckedChangeListener(this)
        binding.txtActivate.setOnClickListener(this)
        return binding.root
    }

    override fun onCheckedChanged(p0: RadioGroup?, p1: Int) {
        val selectedOption: Int = binding.rgPackage!!.checkedRadioButtonId

        when (selectedOption) {
            R.id.rd_pack330 -> {
                packageId = "2"
                Log.e("radio_button", "2")
            }
            R.id.rd_pack1377 -> {
                packageId = "3"
                Log.e("radio_button", "3")
            }


        }
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.txt_activate -> {
                findNavController().navigate(R.id.action_membeActivitionFragment_to_memberSuccessFragment)
            }
        }
    }
}
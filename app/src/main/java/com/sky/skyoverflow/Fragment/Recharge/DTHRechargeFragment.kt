package com.sky.skyoverflow.Fragment.Recharge

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.sky.skyoverflow.R
import com.sky.skyoverflow.SharedPerfence.MyPreferences
import com.sky.skyoverflow.SharedPerfence.PrefConf
import com.sky.skyoverflow.databinding.FragmentDthRechargeBinding
import com.sky.skyoverflow.databinding.FragmentMobileRechargeBinding


class DTHRechargeFragment : Fragment(), AdapterView.OnItemSelectedListener {
    private lateinit var binding: FragmentDthRechargeBinding
    private var mainBal: String? = null
    private var walletType: String? = null
    private var operatorType: String? = null

    var walletList = arrayOf("Select Payment Mode", "Main Wallet")
    var OperatorList = arrayOf("Select Operator", "AIRTEL DIGITAL TV", "DISH TV")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        // Inflate the layout for this fragment
        binding = FragmentDthRechargeBinding.inflate(inflater, container, false)
        mainBal =
            resources.getString(R.string.rupee_sign) + MyPreferences.getInstance(requireContext())
                .getString(
                    PrefConf.USER_MAINWALLETBALANCE, "0"
                )
        binding.txtMainBal.text = mainBal
        binding.paymentSpinner.setOnItemSelectedListener(this)
        binding.OperatorSpinner.setOnItemSelectedListener(this)

        val paymentSpinner =
            ArrayAdapter(requireContext(), R.layout.custom_spinner_layout, walletList)
        paymentSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        val OperatorSpinner =
            ArrayAdapter(requireContext(), R.layout.custom_spinner_layout, OperatorList)
        OperatorSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        binding.paymentSpinner.setAdapter(paymentSpinner)
        binding.OperatorSpinner.setAdapter(OperatorSpinner)

        binding.paymentSpinner.setOnItemSelectedListener(this)
        binding.OperatorSpinner.setOnItemSelectedListener(this)

        return binding.root

    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        val spinner: Spinner = p0 as Spinner

        when (spinner?.id) {
            R.id.payment_spinner -> {
                walletType = p0?.getItemAtPosition(p2).toString()
                Log.e("walletType", walletType!!)
            }
            R.id.Operator_spinner -> {
                operatorType = p0?.getItemAtPosition(p2).toString()
                Log.e("operatorType", operatorType!!)
            }

        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}
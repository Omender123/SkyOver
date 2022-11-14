package com.sky.skyoverflow.Fragment.Recharge

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.viewModels
import com.sky.skyoverflow.Adapter.OperatorAdapter
import com.sky.skyoverflow.Model.Response.Operator
import com.sky.skyoverflow.Model.Response.OperatorResponse
import com.sky.skyoverflow.R
import com.sky.skyoverflow.SharedPerfence.MyPreferences
import com.sky.skyoverflow.SharedPerfence.PrefConf
import com.sky.skyoverflow.Utils.LoadingDialog
import com.sky.skyoverflow.Utils.NetworkResult
import com.sky.skyoverflow.ViewModel.DeshboardViewModel
import com.sky.skyoverflow.ViewModel.MobileOrDTHRechargeViewModel
import com.sky.skyoverflow.ViewModel.RechargeViewModel
import com.sky.skyoverflow.databinding.FragmentDeshboardBinding
import com.sky.skyoverflow.databinding.FragmentMobileRechargeBinding
import com.sky.skyoverflow.databinding.FragmentTransferFundBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MobileRechargeFragment : Fragment(), AdapterView.OnItemSelectedListener,
    RadioGroup.OnCheckedChangeListener, View.OnClickListener {
    private lateinit var binding: FragmentMobileRechargeBinding
    lateinit var loadingDialog: LoadingDialog
    private val rechargeViewModel: RechargeViewModel by viewModels()
    private val mobileOrDTHRechargeRespository: MobileOrDTHRechargeViewModel by viewModels()
    private var mainBal: String? = null
    private var walletType: String? = null
    private var operatorType: String? = null
    private var userId: String? = null
    private lateinit var data: ArrayList<Operator>

    var walletList = arrayOf("Select Payment Mode", "Main Wallet")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMobileRechargeBinding.inflate(inflater, container, false)

        loadingDialog = LoadingDialog(requireContext())

        mainBal =
            resources.getString(R.string.rupee_sign) + MyPreferences.getInstance(requireContext())
                .getString(
                    PrefConf.USER_MAINWALLETBALANCE, "0"
                )

        userId =
            MyPreferences.getInstance(requireContext()).getString(PrefConf.USER_SPONSER_ID, "0")

        rechargeViewModel.fetchOperatorResponse("mobile")
        GetOperatorObservel()
        RechareObservel()
        binding.txtMainBal.text = mainBal
        binding.paymentSpinner.setOnItemSelectedListener(this)
        binding.OperatorSpinner.setOnItemSelectedListener(this)

        val paymentSpinner =
            ArrayAdapter(requireContext(), R.layout.custom_spinner_layout, walletList)
        paymentSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)



        binding.paymentSpinner.setAdapter(paymentSpinner)
        //   binding.OperatorSpinner.setAdapter(OperatorSpinner)

        binding.paymentSpinner.setOnItemSelectedListener(this)
        binding.OperatorSpinner.setOnItemSelectedListener(this)
        binding.rdGroup.setOnCheckedChangeListener(this)

        binding.txtSend.setOnClickListener(this)


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
                operatorType = data?.get(p2)?.OpCode
                Log.e("operatorType", operatorType!!)
            }

        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    fun showLoadingDialog() {
        if (this::loadingDialog.isInitialized && !loadingDialog.isShowing()) {
            loadingDialog.show()
        }
    }

    fun hideLoadingDialog() {
        if (this::loadingDialog.isInitialized && loadingDialog.isShowing()) {
            loadingDialog.dismiss()
        }
    }

    private fun GetOperatorObservel() {
        showLoadingDialog()

        var res = Operator("Select Operator Name", "SON", "")
        data = ArrayList<Operator>()
        data.clear()
        data.add(0, res)

        rechargeViewModel.GetOperatorResponse.observe(viewLifecycleOwner) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    hideLoadingDialog()
                    response.data?.let {
                        data.clear()
                        data.addAll(it.Data)
                        val adapter = OperatorAdapter(requireContext(), data)
                        binding.OperatorSpinner.setAdapter(adapter)

                        Log.e("OperatorResponse", it.toString())
                    }

                }

                is NetworkResult.Error -> {
                    hideLoadingDialog()
                    Toast.makeText(
                        requireContext(),
                        response.message,
                        Toast.LENGTH_SHORT
                    ).show()

                    Log.e("Error", response.message.toString())
                }

                is NetworkResult.Loading -> {
                    showLoadingDialog()
                }
            }
        }
    }

    private fun RechareObservel() {
        mobileOrDTHRechargeRespository.response.observe(viewLifecycleOwner) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    hideLoadingDialog()
                    response.data?.let {
                        Toast.makeText(
                            requireContext(),
                            it.Message,
                            Toast.LENGTH_SHORT
                        ).show()
                        Log.e("OperatorResponse", it.toString())
                    }

                }

                is NetworkResult.Error -> {
                    hideLoadingDialog()
                    Toast.makeText(
                        requireContext(),
                        response.message,
                        Toast.LENGTH_SHORT
                    ).show()

                    Log.e("Error", response.message.toString())
                }

                is NetworkResult.Loading -> {
                    showLoadingDialog()
                }
            }
        }
    }

    override fun onCheckedChanged(p0: RadioGroup?, p1: Int) {
        showLoadingDialog()
        val radioButton = p0?.findViewById(p1) as RadioButton

        if (radioButton.text.toString().equals("Prepaid", true)) {
            rechargeViewModel.fetchOperatorResponse("mobile")
        } else {
            rechargeViewModel.fetchOperatorResponse("postpaid")
        }
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.txt_send -> {
                if (binding.edMobileNo.text.toString().trim().isEmpty()) {
                    Toast.makeText(
                        requireContext(),
                        "Please enter Mobile No.",
                        Toast.LENGTH_SHORT
                    ).show()

                } else if (binding.edAmount.text.toString().trim().isEmpty()) {
                    Toast.makeText(
                        requireContext(),
                       "Please Enter Amount",
                        Toast.LENGTH_SHORT
                    ).show()

                } else if (walletType.equals("Select Payment Mode",true)) {
                    Toast.makeText(
                        requireContext(),
                        "please Select Payment Mode",
                        Toast.LENGTH_SHORT
                    ).show()

                } else {
                    showLoadingDialog()
                    mobileOrDTHRechargeRespository.MobileOrDTHRechargeRequest(
                        binding.edMobileNo.text.toString().trim(),
                        "mobile",
                        operatorType!!,
                        binding.edAmount.text.toString().trim(),
                        userId!!
                    )
                }
            }
        }
    }
}
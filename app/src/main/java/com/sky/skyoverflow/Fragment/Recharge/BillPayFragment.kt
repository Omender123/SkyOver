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
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.sky.skyoverflow.Adapter.OperatorAdapter
import com.sky.skyoverflow.Fragment.Dashboard.IncomeFragmentArgs
import com.sky.skyoverflow.Model.Response.Operator
import com.sky.skyoverflow.R
import com.sky.skyoverflow.SharedPerfence.MyPreferences
import com.sky.skyoverflow.SharedPerfence.PrefConf
import com.sky.skyoverflow.Utils.LoadingDialog
import com.sky.skyoverflow.Utils.NetworkResult
import com.sky.skyoverflow.ViewModel.RechargeViewModel
import com.sky.skyoverflow.databinding.FragmentBillPayBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BillPayFragment : Fragment(), AdapterView.OnItemSelectedListener {
    private lateinit var binding: FragmentBillPayBinding
    lateinit var loadingDialog: LoadingDialog
    private val rechargeViewModel: RechargeViewModel by viewModels()
    private var mainBal: String? = null
    private var walletType: String? = null
    private var operatorType: String? = null
    private lateinit var data: ArrayList<Operator>
    val args: BillPayFragmentArgs by navArgs()
    var title: String? = null
    var walletList = arrayOf("Select Payment Mode", "Main Wallet")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentBillPayBinding.inflate(inflater, container, false)

        loadingDialog = LoadingDialog(requireContext())
        if (args != null) {
            title = args.title
            if (title.equals("Water Bill", true)) {
                rechargeViewModel.fetchOperatorResponse("water")
                binding.edConnectionId.hint = "Connection Id"
            } else if (title.equals("Electric Bill", true)) {
                rechargeViewModel.fetchOperatorResponse("electric")
                binding.edConnectionId.hint = "CA Number"
            } else if (title.equals("Gas Bill", true)) {
                rechargeViewModel.fetchOperatorResponse("gas")
                binding.edConnectionId.hint = "Customer Number"
            }else if (title.equals("Fastag Recharge", true)) {
                rechargeViewModel.fetchOperatorResponse("fastag")
                binding.edConnectionId.hint = "Vehicle Registration Number"
            }
        }
        mainBal =
            resources.getString(R.string.rupee_sign) + MyPreferences.getInstance(requireContext())
                .getString(
                    PrefConf.USER_MAINWALLETBALANCE, "0"
                )


        GetOperatorObservel()
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


}
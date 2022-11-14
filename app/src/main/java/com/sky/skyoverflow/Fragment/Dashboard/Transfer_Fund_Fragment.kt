package com.sky.skyoverflow.Fragment.Dashboard

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.sky.skyoverflow.R
import com.sky.skyoverflow.SharedPerfence.MyPreferences
import com.sky.skyoverflow.SharedPerfence.PrefConf
import com.sky.skyoverflow.Utils.AppUtils
import com.sky.skyoverflow.Utils.LoadingDialog
import com.sky.skyoverflow.Utils.NetworkResult
import com.sky.skyoverflow.ViewModel.AddFundViewModel
import com.sky.skyoverflow.ViewModel.FundTransferViewModel
import com.sky.skyoverflow.databinding.FragmentTransferFundBinding
import com.sky.skyoverflow.databinding.FragmentWalletBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class Transfer_Fund_Fragment : Fragment(), View.OnClickListener {
    private lateinit var binding: FragmentTransferFundBinding
    lateinit var loadingDialog: LoadingDialog
    private var mainBal: String? = null
    private var senderUserId: String? = null
    private var receiverUserId: String? = null

    private val fundTransferViewModel: FundTransferViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTransferFundBinding.inflate(inflater, container, false)
        mainBal =
            resources.getString(R.string.rupee_sign) + MyPreferences.getInstance(requireContext())
                .getString(PrefConf.USER_MAINWALLETBALANCE, "0")
        binding.txtMainBal.text = mainBal


        loadingDialog = LoadingDialog(requireContext())
        senderUserId =
            MyPreferences.getInstance(requireContext()).getString(PrefConf.USER_SPONSER_ID, "0")

        binding.btnCheck.setOnClickListener(this)
        binding.txtSend.setOnClickListener(this)
        CheckReferrelObserval()
        FundTransferObserval()
        return binding.root
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

    override fun onClick(p0: View?) {
        when (p0?.id) {

            R.id.btn_check -> {
                binding.txtSend.visibility = View.GONE
                binding.txtName.visibility = View.GONE
                binding.edAmount.visibility = View.GONE
                receiverUserId = binding.edCheckUsername.text.toString()
                if (receiverUserId!!.isEmpty()) {
                    Toast.makeText(requireContext(), "Please Enter Username", Toast.LENGTH_SHORT)
                        .show()
                    binding.edCheckUsername.requestFocus()
                } else {
                    showLoadingDialog()
                    fundTransferViewModel.CheckReferrelRequest(receiverUserId!!)
                }
            }

            R.id.txt_send -> {
                var amount= binding.edAmount.text.toString()
                if (amount.isEmpty()) {
                    Toast.makeText(requireContext(), "Please Enter Amount", Toast.LENGTH_SHORT)
                        .show()
                    binding.edAmount.requestFocus()
                } else {
                    showLoadingDialog()
                    fundTransferViewModel.FundTransferRequest(senderUserId!!,receiverUserId!!,amount)
                }
            }

        }
    }

    fun CheckReferrelObserval() {
        fundTransferViewModel.CheckReferrel.observe(viewLifecycleOwner) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    hideLoadingDialog()
                    response.data?.let {
                        binding.txtSend.visibility = View.VISIBLE
                        binding.txtName.visibility = View.VISIBLE
                        binding.edAmount.visibility = View.VISIBLE
                        binding.txtName.text = it.checkReferrel!!.sUserName
                        AppUtils.hideSoftKeyboard(requireActivity())
                        //Toast.makeText(requireContext(), it.Message, Toast.LENGTH_SHORT).show()
                    }

                }

                is NetworkResult.Error -> {
                    binding.txtSend.visibility = View.GONE
                    binding.txtName.visibility = View.GONE
                    binding.edAmount.visibility = View.GONE
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

    fun FundTransferObserval() {
        fundTransferViewModel.GetFundTransferResponse.observe(viewLifecycleOwner) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    hideLoadingDialog()
                    response.data?.let {

                        requireActivity().onBackPressed()
                        Toast.makeText(requireContext(), it.Message, Toast.LENGTH_SHORT).show()
                    }

                }

                is NetworkResult.Error -> {
                    binding.txtSend.visibility = View.GONE
                    binding.txtName.visibility = View.GONE
                    binding.edAmount.visibility = View.GONE
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
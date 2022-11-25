package com.sky.skyoverflow.Fragment.Dashboard

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sky.skyoverflow.Adapter.AddFundHistoryAdapter
import com.sky.skyoverflow.Adapter.FundTransferHistoryAdapter
import com.sky.skyoverflow.Adapter.OperatorAdapter
import com.sky.skyoverflow.Adapter.ReHistoryAdapter
import com.sky.skyoverflow.R
import com.sky.skyoverflow.SharedPerfence.MyPreferences
import com.sky.skyoverflow.SharedPerfence.PrefConf
import com.sky.skyoverflow.Utils.LoadingDialog
import com.sky.skyoverflow.Utils.NetworkResult
import com.sky.skyoverflow.ViewModel.AddFundViewModel
import com.sky.skyoverflow.ViewModel.FundHistoryViewModel
import com.sky.skyoverflow.databinding.FragmentAddFundBinding
import com.sky.skyoverflow.databinding.FragmentFundHistoryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FundHistoryFragment : Fragment(), RadioGroup.OnCheckedChangeListener {
    private lateinit var binding: FragmentFundHistoryBinding
    lateinit var loadingDialog: LoadingDialog
    private var userId: String? = null
    private val fundHistoryViewModel: FundHistoryViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFundHistoryBinding.inflate(inflater, container, false)
        loadingDialog = LoadingDialog(requireContext())
        userId =
            MyPreferences.getInstance(requireContext()).getString(PrefConf.USER_SPONSER_ID, "0")

        fundHistoryViewModel.SendFundAddHistoryRequest(userId!!)
        AddFundHistoryObservel()
        FundTransferHistoryObservel()

        binding.rdGroup.setOnCheckedChangeListener(this)

        return binding.root
    }

    private fun FundTransferHistoryObservel() {
        fundHistoryViewModel.GetTransactionHistoryResponse.observe(viewLifecycleOwner) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    hideLoadingDialog()
                    response.data?.let {
                        val mLayoutManager1: RecyclerView.LayoutManager =
                            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
                        binding.recyclerView.setLayoutManager(mLayoutManager1)
                        binding.recyclerView.setItemAnimator(DefaultItemAnimator())
                        var adapter =
                            FundTransferHistoryAdapter(it, requireContext());
                        binding.recyclerView.setAdapter(adapter)
                        Log.e("response", it.toString())
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

    private fun AddFundHistoryObservel() {
        showLoadingDialog()

        fundHistoryViewModel.response.observe(viewLifecycleOwner) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    hideLoadingDialog()
                    response.data?.let {
                        val mLayoutManager1: RecyclerView.LayoutManager =
                            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
                        binding.recyclerView.setLayoutManager(mLayoutManager1)
                        binding.recyclerView.setItemAnimator(DefaultItemAnimator())
                        var adapter =
                            AddFundHistoryAdapter(it, requireContext());
                        binding.recyclerView.setAdapter(adapter)

                        Log.e("response", it.toString())
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

    override fun onCheckedChanged(p0: RadioGroup?, p1: Int) {
        showLoadingDialog()
        val radioButton = p0?.findViewById(p1) as RadioButton

        if (radioButton.text.toString().equals("Fund Transfer", true)) {
            fundHistoryViewModel.SendFundAddHistoryRequest(userId!!)
        } else {
            fundHistoryViewModel.GetTransactionHistoryResponse(userId!!)
        }
    }
}
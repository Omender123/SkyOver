package com.sky.skyoverflow.Fragment.Recharge

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sky.skyoverflow.Adapter.IncomeAdapter
import com.sky.skyoverflow.Adapter.OperatorAdapter
import com.sky.skyoverflow.Adapter.ReHistoryAdapter
import com.sky.skyoverflow.Model.Response.Operator
import com.sky.skyoverflow.R
import com.sky.skyoverflow.SharedPerfence.MyPreferences
import com.sky.skyoverflow.SharedPerfence.PrefConf
import com.sky.skyoverflow.Utils.LoadingDialog
import com.sky.skyoverflow.Utils.NetworkResult
import com.sky.skyoverflow.ViewModel.ReHistoryViewModel
import com.sky.skyoverflow.ViewModel.RechargeViewModel
import com.sky.skyoverflow.databinding.FragmentMobileRechargeBinding
import com.sky.skyoverflow.databinding.FragmentReHistoryBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ReHistoryFragment : Fragment() {

    private lateinit var binding: FragmentReHistoryBinding
    lateinit var loadingDialog: LoadingDialog
    private val reHistoryViewModel: ReHistoryViewModel by viewModels()
    private var userId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentReHistoryBinding.inflate(inflater, container, false)

        loadingDialog = LoadingDialog(requireContext())

        userId =
            MyPreferences.getInstance(requireContext())
                .getString(
                    PrefConf.USER_SPONSER_ID, "0"
                )

        Log.e("userId", userId.toString());
        reHistoryViewModel.fetchReHistoryResponse(userId.toString())
        GetReHistoryObservel()

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


    private fun GetReHistoryObservel() {
        showLoadingDialog()


        reHistoryViewModel.GetReHistoryResponse.observe(viewLifecycleOwner) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    hideLoadingDialog()
                    response.data?.let {
                        val mLayoutManager1: RecyclerView.LayoutManager =
                            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
                        binding.recyclerView.setLayoutManager(mLayoutManager1)
                        binding.recyclerView.setItemAnimator(DefaultItemAnimator())
                        var adapter =
                            ReHistoryAdapter(it, requireContext());
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


}
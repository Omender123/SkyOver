package com.sky.skyoverflow.Fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.sky.skyoverflow.Fragment.Dashboard.TotalEarningsFragmentDirections
import com.sky.skyoverflow.Model.Response.Dashborad
import com.sky.skyoverflow.R
import com.sky.skyoverflow.SharedPerfence.MyPreferences
import com.sky.skyoverflow.SharedPerfence.PrefConf
import com.sky.skyoverflow.Utils.AppUtils
import com.sky.skyoverflow.Utils.LoadingDialog
import com.sky.skyoverflow.Utils.NetworkResult
import com.sky.skyoverflow.ViewModel.DeshboardViewModel
import com.sky.skyoverflow.databinding.FragmentDeshboardBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Deshboard_fragment : Fragment(), View.OnClickListener {
    lateinit var loadingDialog: LoadingDialog
    private lateinit var binding: FragmentDeshboardBinding
    private val deshboardViewModel: DeshboardViewModel by viewModels()
    private var userId: String? = null
    private var dashboradResponse: Dashborad? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDeshboardBinding.inflate(inflater, container, false)
        AppUtils.checkAndRequestPermissions(requireActivity())
        loadingDialog = LoadingDialog(requireContext())
        userId =
            MyPreferences.getInstance(requireContext()).getString(PrefConf.USER_SPONSER_ID, "0")

        deshboardViewModel.fetchDeshBoardResponse(userId!!)
        Log.e("userId", userId!!)
        GetDashboardObservel();
        binding.imgWallets.setOnClickListener(this)
        binding.imgAdd.setOnClickListener(this)
        binding.imgTransfer.setOnClickListener(this)
        binding.imgMobileChar.setOnClickListener(this)
        binding.imgDthChar.setOnClickListener(this)
        binding.txtBuy.setOnClickListener(this)
        binding.txtViewAll.setOnClickListener(this)
        binding.reHistory.setOnClickListener(this)
        binding.imgWaterChar.setOnClickListener(this)
        binding.imgElectChar.setOnClickListener(this)
        binding.imgGasChar.setOnClickListener(this)
        binding.imgFastChar.setOnClickListener(this)

        return binding.root
    }

    private fun GetDashboardObservel() {
        showLoadingDialog()
        deshboardViewModel.GetDeshBoardResponse.observe(viewLifecycleOwner) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    hideLoadingDialog()
                    response.data?.let {
                        dashboradResponse = it.Data[0]
                        MyPreferences.getInstance(requireContext()).putString(
                            PrefConf.USER_MAINWALLETBALANCE,
                            it.Data[0].MainwalletBallance
                        )
                        binding.txtTotalIn.text =
                            resources.getString(R.string.rupee_sign) + it.Data[0].TotalIncome
                        binding.txtMthIn.text =
                            resources.getString(R.string.rupee_sign) + it.Data[0].matchingincome
                        binding.txtLoyIn.text =
                            resources.getString(R.string.rupee_sign) + it.Data[0].singlelineincome
                        binding.txtLevelIn.text =
                            resources.getString(R.string.rupee_sign) + it.Data[0].LevelIncome
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

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.img_wallets -> {
                var data = bundleOf()
                data?.putSerializable("Dashborad", dashboradResponse)
                findNavController().navigate(R.id.action_Dashboard_to_walletFragment, data)
            }

            R.id.img_add -> {
                findNavController().navigate(R.id.action_Dashboard_to_add_FundFragment)
            }

            R.id.img_transfer -> {
                findNavController().navigate(R.id.action_Dashboard_to_transfer_Fund_Fragment)
            }

            R.id.img_mobile_char -> {
                findNavController().navigate(R.id.action_Dashboard_to_mobileRechargeFragment)
            }

            R.id.img_dth_char -> {
                findNavController().navigate(R.id.action_Dashboard_to_DTHRechargeFragment)
            }
            R.id.txt_buy -> {
                findNavController().navigate(R.id.action_Dashboard_to_membeActivitionFragment)
            }

            R.id.txt_viewAll -> {
                findNavController().navigate(R.id.action_Dashboard_to_totalEarningsFragment)
            }

            R.id.re_history -> {
                findNavController().navigate(R.id.action_Dashboard_to_reHistoryFragment)
            }

            R.id.img_water_char -> {
                val directions = Deshboard_fragmentDirections.actionDashboardToWaterBillFragment()
                directions.title = "Water Bill"
                findNavController().navigate(directions)
            }
            R.id.img_elect_char -> {
                val directions = Deshboard_fragmentDirections.actionDashboardToWaterBillFragment()
                directions.title = "Electric Bill"
                findNavController().navigate(directions)
            }

            R.id.img_gas_char -> {
                val directions = Deshboard_fragmentDirections.actionDashboardToWaterBillFragment()
                directions.title = "Gas Bill"
                findNavController().navigate(directions)
            }

            R.id.img_fast_char -> {
                val directions = Deshboard_fragmentDirections.actionDashboardToWaterBillFragment()
                directions.title = "Fastag Recharge"
                findNavController().navigate(directions)
            }


        }
    }
}
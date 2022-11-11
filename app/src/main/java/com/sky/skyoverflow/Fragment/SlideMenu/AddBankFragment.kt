package com.sky.skyoverflow.Fragment.SlideMenu

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.sky.skyoverflow.Model.RequestBody.ProfileDetailsUpdateBody
import com.sky.skyoverflow.Model.Response.ProfileDetails
import com.sky.skyoverflow.R
import com.sky.skyoverflow.SharedPerfence.MyPreferences
import com.sky.skyoverflow.SharedPerfence.PrefConf
import com.sky.skyoverflow.Utils.LoadingDialog
import com.sky.skyoverflow.Utils.NetworkResult
import com.sky.skyoverflow.ViewModel.ProfileUpdateViewModel
import com.sky.skyoverflow.databinding.FragmentAddBankBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddBankFragment : Fragment(), View.OnClickListener {
    private lateinit var binding: FragmentAddBankBinding
    lateinit var loadingDialog: LoadingDialog
    private val profileUpdate: ProfileUpdateViewModel by viewModels()
    private var FirstName: String? = null
    private var Mobile: String? = null
    private var profileDetails: ProfileDetails? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddBankBinding.inflate(inflater, container, false)

        loadingDialog = LoadingDialog(requireContext())
        profileDetails = MyPreferences.getInstance(requireContext())
            .getMemberDetails(PrefConf.PROFILE_DETAILS, null)

        Log.e("profileDetails", profileDetails.toString())

        if (profileDetails != null) {
            binding.edUser.setText(profileDetails?.payeename ?: "")
            binding.edBankName.setText(profileDetails?.bankname ?: "")
            binding.edAccNo.setText(profileDetails?.accountNo ?: "")
            binding.edBranchName.setText(profileDetails?.Branch ?: "")
            binding.edIfscNo.setText(profileDetails?.IFSCCode ?: "")
        }
        FirstName = MyPreferences.getInstance(requireContext()).getString(PrefConf.USER_FNAME, "")
        Mobile = MyPreferences.getInstance(requireContext()).getString(PrefConf.USER_MOBILE, "")

        binding.btnSave.setOnClickListener(this)
        AddBankDetailsObservel()
        return binding.root
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.btn_save -> {

                if (binding.edUser.text.isEmpty() || binding.edAccNo.text.isEmpty() || binding.edBranchName.text.isEmpty() ||
                    binding.edBankName.text.isEmpty() || binding.edIfscNo.text.isEmpty()
                ) {
                    Toast.makeText(requireContext(), "All Field is require", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    showLoadingDialog()
                    val body = ProfileDetailsUpdateBody(
                        FirstName!!,
                        null,
                        Mobile!!,
                        null,
                        null,
                        null,
                        null,
                        null,
                        binding.edBankName.text.toString(),
                        binding.edUser.text.toString(),
                        binding.edAccNo.text.toString(),
                        binding.edIfscNo.text.toString(),
                        binding.edBranchName.text.toString()
                    )
                    Log.e("body", body.toString())
                    profileUpdate.fetchUpdateProfileDetailsResponse(body)

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

    private fun AddBankDetailsObservel() {


        profileUpdate.response.observe(viewLifecycleOwner) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    hideLoadingDialog()
                    response.data?.let {

                        Toast.makeText(
                            requireContext(),
                            "Successfully Add Bank Details",
                            Toast.LENGTH_SHORT
                        )
                            .show()

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
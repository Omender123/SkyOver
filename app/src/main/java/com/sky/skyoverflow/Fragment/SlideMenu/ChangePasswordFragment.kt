package com.sky.skyoverflow.Fragment.SlideMenu

import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.sky.skyoverflow.Model.RequestBody.ProfileDetailsUpdateBody
import com.sky.skyoverflow.R
import com.sky.skyoverflow.SharedPerfence.MyPreferences
import com.sky.skyoverflow.SharedPerfence.PrefConf
import com.sky.skyoverflow.Utils.LoadingDialog
import com.sky.skyoverflow.Utils.NetworkResult
import com.sky.skyoverflow.ViewModel.ChangePasswordViewModel
import com.sky.skyoverflow.ViewModel.ProfileUpdateViewModel
import com.sky.skyoverflow.databinding.FragmentChangePasswordBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ChangePasswordFragment : Fragment(), View.OnClickListener {
    private lateinit var binding: FragmentChangePasswordBinding
    lateinit var loadingDialog: LoadingDialog
    private val changePasswordViewModel: ChangePasswordViewModel by viewModels()
    private var Mobile: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentChangePasswordBinding.inflate(inflater, container, false)

        Mobile = MyPreferences.getInstance(requireContext()).getString(PrefConf.USER_MOBILE, "")



        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadingDialog = LoadingDialog(requireContext())
        binding.btnSave.setOnClickListener(this)
        ChangePasswordObservel()
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.btn_save -> {

                if (binding.edOldPass.text.isEmpty() || binding.edNewPass.text.isEmpty() || binding.edCNewPass.text.isEmpty()) {
                    Toast.makeText(requireContext(), "All Field is require", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    showLoadingDialog()
                    changePasswordViewModel.ChangePassword(
                        Mobile!!,
                        binding.edOldPass.text.toString(),
                        binding.edNewPass.text.toString(),
                        binding.edCNewPass.text.toString()
                    )



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

    private fun ChangePasswordObservel() {

        changePasswordViewModel.ChangePasswordResponse.observe(viewLifecycleOwner) { response ->
            when (response) {
                is NetworkResult.Success -> {

                    hideLoadingDialog()
                    response.data?.let {

                        Toast.makeText(
                            requireContext(),
                            it.Message,
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
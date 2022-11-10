package com.sky.skyoverflow.Fragment.SlideMenu

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
import com.sky.skyoverflow.Model.Response.Dashborad
import com.sky.skyoverflow.Model.Response.ProfileDetails
import com.sky.skyoverflow.R
import com.sky.skyoverflow.SharedPerfence.MyPreferences
import com.sky.skyoverflow.SharedPerfence.PrefConf
import com.sky.skyoverflow.Utils.LoadingDialog
import com.sky.skyoverflow.Utils.NetworkResult
import com.sky.skyoverflow.ViewModel.DeshboardViewModel
import com.sky.skyoverflow.ViewModel.ProfileDetailsViewModel
import com.sky.skyoverflow.databinding.FragmentDeshboardBinding
import com.sky.skyoverflow.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment(), View.OnClickListener {
    private lateinit var binding: FragmentProfileBinding
    lateinit var loadingDialog: LoadingDialog
    private val profileDetailsViewModel: ProfileDetailsViewModel by viewModels()
    private var userId: String? = null
    private var profileDetails: ProfileDetails? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =  FragmentProfileBinding.inflate(inflater, container, false)
        loadingDialog = LoadingDialog(requireContext())
        userId =
            MyPreferences.getInstance(requireContext()).getString(PrefConf.USER_SPONSER_ID, "0")

        profileDetailsViewModel.fetchProfileDetailsResponse(userId!!)
        Log.e("userId", userId!!)
        GetProfileDetailsObservel();

        binding.txtEdit.setOnClickListener(this)
        return binding.root
    }

    private fun GetProfileDetailsObservel() {

        showLoadingDialog()
        profileDetailsViewModel.response.observe(viewLifecycleOwner) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    hideLoadingDialog()
                    response.data?.let {
                        profileDetails = it.profileDetails
                        binding.txtUsername.text = it.profileDetails.loginid
                        binding.txtFullname.text = it.profileDetails.name+" "+it.profileDetails.lName
                        binding.txtEmail.text = it.profileDetails.emailID
                        binding.txtMobile.text = it.profileDetails.memMobile
                        binding.txtShare.text = it.profileDetails.loginid
                        MyPreferences.getInstance(requireContext())
                            .putString(PrefConf.USER_SPONSER_ID, it.profileDetails.loginid);
                        MyPreferences.getInstance(requireContext())
                            .putString(PrefConf.USER_FNAME, it.profileDetails.name);
                        MyPreferences.getInstance(requireContext())
                            .putString(PrefConf.USER_LNAME, it.profileDetails.lName);
                        MyPreferences.getInstance(requireContext())
                            .putString(PrefConf.USER_MOBILE, it.profileDetails.memMobile);
                        MyPreferences.getInstance(requireContext())
                            .putString(PrefConf.USER_EMAIL, it.profileDetails.emailID);

                        Log.e("data",it.toString())
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
        when(p0?.id){
            R.id.txt_edit ->{
                var data = bundleOf()
                data?.putSerializable("profileDetails", profileDetails)
                findNavController().navigate(R.id.action_profileFragment_to_editProfileFragment,data)
            }
        }
    }

}
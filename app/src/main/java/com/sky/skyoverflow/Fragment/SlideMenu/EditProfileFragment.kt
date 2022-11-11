package com.sky.skyoverflow.Fragment.SlideMenu

import android.app.DatePickerDialog
import android.os.Build
import android.os.Bundle
import android.text.format.DateFormat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.sky.skyoverflow.Model.RequestBody.ProfileDetailsUpdateBody
import com.sky.skyoverflow.Model.Response.ProfileDetails
import com.sky.skyoverflow.R
import com.sky.skyoverflow.Utils.LoadingDialog
import com.sky.skyoverflow.Utils.NetworkResult
import com.sky.skyoverflow.ViewModel.ProfileUpdateViewModel
import com.sky.skyoverflow.databinding.FragmentEditProfileBinding
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*


@AndroidEntryPoint
class EditProfileFragment : Fragment(), View.OnClickListener, RadioGroup.OnCheckedChangeListener,
    DatePickerDialog.OnDateSetListener {
    private lateinit var binding: FragmentEditProfileBinding
    lateinit var loadingDialog: LoadingDialog
    private val profileUpdate: ProfileUpdateViewModel by viewModels()
    private var gender: String? = null
    private var profileDetails: ProfileDetails? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        binding = FragmentEditProfileBinding.inflate(inflater, container, false)
        loadingDialog = LoadingDialog(requireContext())
        profileDetails = arguments?.getSerializable("profileDetails") as ProfileDetails
        Log.e("profileDetails", "shshsh" + profileDetails)


        binding.edFname.setText(profileDetails?.name ?: "")
        binding.edLname.setText(profileDetails?.lName ?: "")
        binding.edFathername.setText(profileDetails?.fatHusName ?: "")
        if (profileDetails?.dob != null) {
            val parser = SimpleDateFormat("yyyy-MM-dd")
            val formatter = SimpleDateFormat("dd-MM-yyyy")
            val output: String = formatter.format(parser.parse(profileDetails?.dob))
            binding.edDate.setText(output)
        }
        binding.edPanNo.setText(profileDetails?.pannumber ?: "")
        binding.edMobile.setText(profileDetails?.memMobile ?: "")
        binding.edEmail.setText(profileDetails?.emailID ?: "")

        if (profileDetails?.gender.equals("0", true)) {
            binding.rdMale.isChecked = true
            gender = "male"
        } else if (profileDetails?.gender.equals("1", true)) {
            binding.rdFemale.isChecked = true
            gender = "female"
        }

        binding.rgGender.setOnCheckedChangeListener(this)
        binding.btnUpdate.setOnClickListener(this)
        binding.edDate.setOnClickListener(this)
        GetUpdateProfileDetailsObservel()
        return binding.root
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {

            R.id.btn_update -> {
                if (binding.edFname.text.isEmpty() || binding.edLname.text.isEmpty() || binding.edFathername.text.isEmpty()
                    || binding.edMobile.text.isEmpty() || binding.edDate.text.isEmpty() ||
                    binding.edPanNo.text.isEmpty() || gender == null
                ) {
                    Toast.makeText(requireContext(), "All Field is require", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    showLoadingDialog()
                    val body = ProfileDetailsUpdateBody(
                        binding.edFname.text.toString(),
                        binding.edLname.text.toString(),
                        binding.edMobile.text.toString(),
                        binding.edFathername.text.toString(),
                        binding.edDate.text.toString(),
                        binding.edPanNo.text.toString(),
                        binding.edEmail.text.toString(),
                        gender
                    )
                    Log.e("body", body.toString())

                    profileUpdate.fetchUpdateProfileDetailsResponse(body)

                }
            }

            R.id.ed_date -> {
                showPickerDialog()
            }
        }
    }

    private fun showPickerDialog() {
        val today = Calendar.getInstance()
        val dtPickerDlg =
            DatePickerDialog(
                requireContext(),
                android.R.style.Theme_Holo_Dialog,
                this,
                today.get(Calendar.YEAR),
                today.get(Calendar.MONTH),
                today.get(Calendar.DAY_OF_MONTH)
            )
        dtPickerDlg.datePicker.spinnersShown = true
        dtPickerDlg.datePicker.calendarViewShown = false
        dtPickerDlg.setCancelable(false)
        dtPickerDlg.setTitle("Select Date Of Birth")
        dtPickerDlg.show()
    }

    override fun onCheckedChanged(p0: RadioGroup?, p1: Int) {
        val radioButton = p0?.findViewById(p1) as RadioButton
        gender = radioButton.text.toString()

    }

    override fun onDateSet(p0: DatePicker?, p1: Int, p2: Int, p3: Int) {
        val c = Calendar.getInstance()
        c[Calendar.YEAR] = p1
        c[Calendar.MONTH] = p2
        c[Calendar.DAY_OF_MONTH] = p3
        val mDate = DateFormat.format("dd-MM-yyyy", c).toString()
        binding.edDate.setText(mDate)
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

    private fun GetUpdateProfileDetailsObservel() {


        profileUpdate.response.observe(viewLifecycleOwner) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    hideLoadingDialog()
                    response.data?.let {

                        Toast.makeText(requireContext(), it.Message.toString(), Toast.LENGTH_SHORT)
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
package com.sky.skyoverflow.Authentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import com.sky.skyoverflow.Model.RequestBody.ResgisterBody
import com.sky.skyoverflow.R
import com.sky.skyoverflow.Utils.AppUtils
import com.sky.skyoverflow.Utils.LoadingDialog
import com.sky.skyoverflow.Utils.NetworkResult
import com.sky.skyoverflow.ViewModel.ResgisterViewModel
import com.sky.skyoverflow.databinding.ActivityResgisterBinding
import dagger.hilt.android.AndroidEntryPoint
import org.json.JSONObject

@AndroidEntryPoint
class Resgister : AppCompatActivity(), View.OnClickListener {
    lateinit var loadingDialog: LoadingDialog
    private lateinit var binding: ActivityResgisterBinding
    private val resgisterViewModel: ResgisterViewModel by viewModels()

    var SponserId: String? = null
    var FirstName: String? = null
    var LastName: String? = null
    var Mobile: String? = null
    var StateId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityResgisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        AppUtils.setStatusBarGradiant(this)
        loadingDialog = LoadingDialog(this)
        binding.btnRes.setOnClickListener(this)
        binding.linearRes.setOnClickListener(this)

        binding.imgBack.setOnClickListener(this)

        resgisterViewModel.fetchStateResponse()
        StateObserveral()
        ResgisterObserveral()


    }

    private fun ResgisterObserveral() {
        resgisterViewModel.ResgisterMember.observe(this){ response ->
                when(response){
                    is NetworkResult.Success->{
                        hideLoadingDialog()

                        var data =JSONObject(response.data!!.string())
                        Log.e("response", ""+data.getString("Data"))
                        Toast.makeText(this,""+data.getString("Message"),Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this,VerifyOtp::class.java).putExtra("mobile",Mobile!!).putExtra("type","resister"))
                    }

                    is NetworkResult.Error->{
                        hideLoadingDialog()
                        Toast.makeText(this,response.message.toString(),Toast.LENGTH_SHORT).show()
                        Log.e("Error", response.message.toString())
                    }
                    is NetworkResult.Loading->{
                        showLoadingDialog()
                    }
                }

        }
    }

    private fun StateObserveral() {
        showLoadingDialog()
        resgisterViewModel.getState.observe(this) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    hideLoadingDialog()
                    response.data?.let {
                        var stateName = ArrayList<String>();
                        stateName.clear()
                        stateName.add(0, "Select State")
                        for (i in 0..response.data.Data.size - 1) {
                            stateName.add(response.data.Data.get(i).StateName)

                        }
                        val adapterCourses = ArrayAdapter<String>(
                            this,
                            R.layout.custom_spinner_layout,
                            stateName
                        )

                        binding.appcompactSpinner.adapter = adapterCourses
                        binding.appcompactSpinner.onItemSelectedListener =
                            object : AdapterView.OnItemSelectedListener {
                                override fun onItemSelected(
                                    p0: AdapterView<*>?,
                                    p1: View?,
                                    p2: Int,
                                    p3: Long
                                ) {
                                    if (p2 != 0) {
                                        StateId = response.data.Data.get(p2 - 1).Stateid.toString()
                                        Log.e("StateId", StateId.toString())
                                    }

                                }

                                override fun onNothingSelected(parent: AdapterView<*>?) {

                                }

                            }

                    }

                }

                is NetworkResult.Error -> {
                    hideLoadingDialog()
                    Toast.makeText(
                        this,
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
            R.id.btn_res -> {
                SponserId = binding.edSponserId.text.toString().trim()
                FirstName = binding.edFirstName.text.toString().trim()
                LastName = binding.edLastName.text.toString().trim()
                Mobile = binding.edMobile.text.toString().trim()
                if (SponserId!!.isEmpty()) {
                    binding.edSponserId.requestFocus()
                    Toast.makeText(this, "Please enter SponserId", Toast.LENGTH_SHORT).show()
                } else if (FirstName!!.isEmpty()) {
                    binding.edFirstName.requestFocus()
                    Toast.makeText(this, "Please enter First Name", Toast.LENGTH_SHORT).show()
                } else if (LastName!!.isEmpty()) {
                    binding.edLastName.requestFocus()
                    Toast.makeText(this, "Please enter Last Name", Toast.LENGTH_SHORT).show()
                } else if (Mobile!!.isEmpty()) {
                    binding.edMobile.requestFocus()
                    Toast.makeText(this, "Please enter Mobile No.", Toast.LENGTH_SHORT).show()
                } else if (StateId==null) {
                    binding.appcompactSpinner.requestFocus()
                    Toast.makeText(this, "Please Select State", Toast.LENGTH_SHORT).show()
                } else {
                    showLoadingDialog()
                    var body = ResgisterBody(
                        SponserId.toString(),
                        FirstName.toString(),
                        LastName.toString(),
                        Mobile.toString(),
                        StateId!!
                    )
                    resgisterViewModel.fetchResgisterResponse(body)
                    showLoadingDialog()
                }
            }

            R.id.linear_res -> {
                startActivity(Intent(this, Login::class.java))
            }

            R.id.img_back ->{
                onBackPressed()
            }
        }
    }
}
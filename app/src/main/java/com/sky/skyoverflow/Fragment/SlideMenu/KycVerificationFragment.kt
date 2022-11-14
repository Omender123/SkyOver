package com.sky.skyoverflow.Fragment.SlideMenu

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.sky.skyoverflow.R
import com.sky.skyoverflow.databinding.FragmentKycVerificationBinding


class KycVerificationFragment : Fragment(), View.OnClickListener {
    private lateinit var binding: FragmentKycVerificationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =FragmentKycVerificationBinding.inflate(inflater, container, false)

        binding.lytPhoto.setOnClickListener(this)
        binding.lytDoc.setOnClickListener(this)


        return binding.root
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.lyt_photo ->{
                findNavController().navigate(R.id.action_kycVerificationFragment_to_uploadPhotoFragment)
            }

            R.id.lyt_doc ->{
                findNavController().navigate(R.id.action_kycVerificationFragment_to_uploadDocFragment)
            }
        }
    }

}
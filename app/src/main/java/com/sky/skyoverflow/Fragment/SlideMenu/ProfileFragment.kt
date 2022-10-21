package com.sky.skyoverflow.Fragment.SlideMenu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.sky.skyoverflow.R
import com.sky.skyoverflow.databinding.FragmentProfileBinding


class ProfileFragment : Fragment(), View.OnClickListener {
    private lateinit var binding: FragmentProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =  FragmentProfileBinding.inflate(inflater, container, false)

        binding.txtEdit.setOnClickListener(this)
        return binding.root
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.txt_edit ->{
                findNavController().navigate(R.id.action_profileFragment_to_editProfileFragment)
            }
        }
    }

}
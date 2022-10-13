package com.sky.skyoverflow.Fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sky.skyoverflow.Model.Dashborad
import com.sky.skyoverflow.Model.DashboradResponse
import com.sky.skyoverflow.R
import com.sky.skyoverflow.databinding.FragmentDeshboardBinding
import com.sky.skyoverflow.databinding.FragmentMemberBinding
import com.sky.skyoverflow.databinding.FragmentWalletBinding


class MemberFragment : Fragment() {
    private lateinit var binding: FragmentMemberBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMemberBinding.inflate(inflater, container, false)


        return binding.root
    }

}
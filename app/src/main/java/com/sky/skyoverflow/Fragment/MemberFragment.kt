package com.sky.skyoverflow.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sky.skyoverflow.databinding.FragmentMemberBinding


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
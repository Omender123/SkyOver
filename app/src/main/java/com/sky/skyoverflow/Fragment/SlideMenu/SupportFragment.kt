package com.sky.skyoverflow.Fragment.SlideMenu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sky.skyoverflow.R
import com.sky.skyoverflow.databinding.FragmentSupportBinding


class SupportFragment : Fragment() {
    private lateinit var binding: FragmentSupportBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSupportBinding.inflate(inflater, container, false)

        return binding.root
    }

}
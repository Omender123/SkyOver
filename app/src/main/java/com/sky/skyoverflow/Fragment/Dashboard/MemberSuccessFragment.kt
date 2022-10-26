package com.sky.skyoverflow.Fragment.Dashboard

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.sky.skyoverflow.MainActivity
import com.sky.skyoverflow.databinding.FragmentMemberSuccessBinding


class MemberSuccessFragment : Fragment() {
    private lateinit var binding: FragmentMemberSuccessBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentMemberSuccessBinding.inflate(inflater, container, false)

        // This callback will only be called when MyFragment is at least Started.
        // This callback will only be called when MyFragment is at least Started.
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    startActivity(Intent(requireActivity(), MainActivity::class.java))
                    requireActivity().finish()
                }
            }

        requireActivity().onBackPressedDispatcher.addCallback(requireActivity(), callback)

        binding.txtBack.setOnClickListener {
            requireActivity().onBackPressed()

        }
        binding.txtBack1.setOnClickListener {
            requireActivity().onBackPressed()

        }

        return binding.root
    }

}
package com.sky.skyoverflow.Fragment.Dashboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.sky.skyoverflow.R
import com.sky.skyoverflow.databinding.FragmentTotalEarningsBinding
import kotlinx.android.synthetic.main.fragment_total_earnings.*


class TotalEarningsFragment : Fragment(), View.OnClickListener {
    private lateinit var binding:FragmentTotalEarningsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTotalEarningsBinding.inflate(inflater, container, false)
        binding.btnLevel.setOnClickListener(this)
        binding.btnRoi.setOnClickListener(this)
        binding.btnBonus.setOnClickListener(this)

        return binding.root
    }

    override fun onClick(p0: View?) {
        when(p0?.id){

            R.id.btn_level ->{
                val directions = TotalEarningsFragmentDirections.actionTotalEarningsFragmentToIncomeFragment()
                directions.title = "Level Income"
                findNavController().navigate(directions)
            }
            R.id.btn_roi ->{
                val directions = TotalEarningsFragmentDirections.actionTotalEarningsFragmentToIncomeFragment()
                directions.title = "ROI Income"
                findNavController().navigate(directions)
            }
            R.id.btn_bonus ->{
                val directions = TotalEarningsFragmentDirections.actionTotalEarningsFragmentToIncomeFragment()
                directions.title = "Bonus ROI Income"
                findNavController().navigate(directions)
            }
        }
    }
}
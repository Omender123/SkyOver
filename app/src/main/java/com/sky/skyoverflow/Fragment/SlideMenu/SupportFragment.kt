package com.sky.skyoverflow.Fragment.SlideMenu

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
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

        binding.lytEmail.setOnClickListener {
            try {
                val intent = Intent(Intent.ACTION_SENDTO)
                intent.data = Uri.parse("mailto:support@skyover.in") // only email apps should handle this
                //  intent.putExtra(Intent.EXTRA_EMAIL, arrayOf("example.yahoo.com"))
                //intent.putExtra(Intent.EXTRA_SUBJECT, "App feedback")
                startActivity(intent)
            } catch (ex: ActivityNotFoundException) {
                Toast.makeText(
                    requireContext(),
                    "There are no email client installed on your device.", Toast.LENGTH_SHORT
                ).show()
            }
        }

        return binding.root
    }

}
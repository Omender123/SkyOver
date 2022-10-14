package com.sky.skyoverflow.Fragment.Dashboard

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.view.get
import androidx.fragment.app.Fragment
import com.sky.skyoverflow.R
import com.sky.skyoverflow.Utils.AppUtils
import com.sky.skyoverflow.databinding.FragmentAddFundBinding
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File


class Add_FundFragment : Fragment(), AdapterView.OnItemSelectedListener, View.OnClickListener {
    private lateinit var binding: FragmentAddFundBinding
    private var walletType: String? = null
    private var paymentMethod: String? = null
    var permissionStatus = false
    private val PICK_PHOTO_FOR_AVATAR = 1
    private var uri: Uri? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddFundBinding.inflate(inflater, container, false)

        val adapterWallets = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.wallets_list,
            R.layout.custom_spinner_layout
        )

        val adapterPaymentMethod = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.pay_method_list,
            R.layout.custom_spinner_layout
        )

        binding.walletsSpinner.adapter = adapterWallets
        binding.paymentSpinner.adapter = adapterPaymentMethod

        binding.walletsSpinner.onItemSelectedListener = this
        binding.paymentSpinner.onItemSelectedListener = this
        binding.txtSend.setOnClickListener(this)
        binding.txtChoose.setOnClickListener(this)

        return binding.root
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        val spinner: Spinner = p0 as Spinner

        when (spinner?.id) {
            R.id.wallets_spinner -> {
                walletType = p0?.getItemAtPosition(p2).toString()
                Log.e("walletType", walletType!!)
            }
            R.id.payment_spinner -> {
                paymentMethod = p0?.getItemAtPosition(p2).toString()
                Log.e("paymentMethod", paymentMethod!!)
            }

        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.txt_send -> {
                var amount: String = binding.edAmount.text.toString().trim()
                var remarks: String = binding.edRemarks.text.toString().trim()
                var fileName: String = binding.txtChoose.text.toString().trim()
                if (amount.isEmpty()) {
                    binding.edAmount.requestFocus()
                    Toast.makeText(requireContext(), "please enter amount", Toast.LENGTH_SHORT)
                        .show()
                } else if (walletType.equals("Select Wallet", true)) {
                    binding.walletsSpinner.requestFocus()
                    Toast.makeText(requireContext(), "Please Select Wallet", Toast.LENGTH_SHORT)
                        .show()
                } else if (paymentMethod.equals("Select Payment Method", true)) {
                    binding.paymentSpinner.requestFocus()
                    Toast.makeText(
                        requireContext(),
                        "Please Select Payment Method",
                        Toast.LENGTH_SHORT
                    ).show()

                } else if (fileName.equals("Upload Screenshot", true)) {
                    binding.txtChoose.requestFocus()
                    Toast.makeText(requireContext(), "please Choose File", Toast.LENGTH_SHORT)
                        .show()
                } else {

                }
            }

            R.id.txt_choose -> {
                galleryPicker()
            }
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            AppUtils.PERMISSION_REQUEST_CODE -> {
                if (grantResults.size > 0 && grantResults[0] === PackageManager.PERMISSION_GRANTED) {
                    permissionStatus = true
                } else {
                    permissionStatus = false
                    val msg = "Please Allow Permission to share."
                    customAlert(msg)
                }
                return
            }
        }
    }

    private fun customAlert(msg: String) {
        val alertDialog: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        alertDialog.setMessage(msg)
        alertDialog.setCancelable(false)
        alertDialog.setPositiveButton("OK",
            DialogInterface.OnClickListener { dialog, id -> dialog.dismiss() }).show()
    }

    private fun galleryPicker() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        startActivityForResult(intent, PICK_PHOTO_FOR_AVATAR)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode === PICK_PHOTO_FOR_AVATAR && resultCode === Activity.RESULT_OK) {
            if (data == null)
                return
            uri = data.getData()
            println("urii  " + uri + " " + uri!!.getPath())
            val path =  getPath(uri)
            println("urii path $path")
            if (path != null && path != "") {
                binding.txtChoose.text = path
                val file = File(path)
                uploadImage(file)
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun uploadImage(file: File) {
        /* helper?.saveCartProductId(PrefKeys.KEY_CART_PRODUCT_ID, it.productId)*/

        /*   val requestFile: RequestBody =
               RequestBody.create("multipart/form-data".toMediaTypeOrNull(), file)
           val profileImage: MultipartBody.Part =
               MultipartBody.Part.createFormData("file", file.name, requestFile)*/

     /*   val requestFile =
            file.asRequestBody(requireContext().contentResolver.getType(uri!!)?.toMediaTypeOrNull())
        val body = MultipartBody.Part.createFormData("file", file.name, requestFile)

        val productId: RequestBody = create(
            "multipart/form-data".toMediaTypeOrNull(),
            helper?.getCartProductId(PrefKeys.KEY_CART_PRODUCT_ID).toString()
        )
*/

    }


    //method to get the file path from uri
    @SuppressLint("Range")
    fun getPath(uri: Uri?): String? {
        var cursor: Cursor =
            requireActivity().getContentResolver().query(uri!!, null, null, null, null)!!
        cursor.moveToFirst()
        var document_id: String = cursor.getString(0)
        document_id = document_id.substring(document_id.lastIndexOf(":") + 1)
        cursor.close()
        cursor = requireActivity().getContentResolver().query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            null, MediaStore.Images.Media._ID + " = ? ", arrayOf(document_id), null
        )!!
        cursor.moveToFirst()
        val path: String = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA))
        cursor.close()
        return path
    }


}
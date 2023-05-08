package com.example.movieapp.fragment

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.movieapp.R

class ErrorDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val message = requireArguments().getString(ARG_MESSAGE)
        val builder = AlertDialog.Builder(requireActivity())
        builder.setTitle(getString(R.string.error_dialog_fragment_title))
            .setMessage(message)
            .setPositiveButton(getString(R.string.error_dialog_fragment_button_ok)) { dialog: DialogInterface, _: Int ->
                dialog.dismiss()
            }
        return builder.create()
    }

    companion object {
        const val TAG = "ErrorDialogFragment"
        private const val ARG_MESSAGE = "ARG_MESSAGE"

        fun newInstance(message: String): ErrorDialogFragment {
            val args = Bundle()
            args.putString(ARG_MESSAGE, message)
            val fragment = ErrorDialogFragment()
            fragment.arguments = args
            return fragment
        }
    }
}

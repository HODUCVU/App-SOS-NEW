package com.example.appsos.Dialog

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class DialogAddNumberPhone(private val message : String) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
     val builder = AlertDialog.Builder(this.requireActivity())
        builder.setMessage(message)
            .setPositiveButton("OK",
                DialogInterface.OnClickListener { dialog, which ->
                }
            )
        return builder.create()
    }
}
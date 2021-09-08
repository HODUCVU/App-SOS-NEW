package com.example.appsos.getDataSOS

import android.Manifest
import android.content.pm.PackageManager
import android.provider.ContactsContract
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.example.appsos.MainActivity
import com.example.appsos.dataObject.DataContact
import com.example.appsos.dataObject.listContact

class ReadContact(private val context: MainActivity) {
    fun readContact() {
        try {
            if (
                ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    context,
                    Array(1) { Manifest.permission.READ_CONTACTS },
                    //context.request
                111
                )
            }
            else {
                queryListContact()
            }
        }
        catch (e : Exception) {
            Log.e("Error in one: ",e.toString())
            Toast.makeText(context,e.message, Toast.LENGTH_LONG).show()
        }
    }
    fun queryListContact() {
        try {
            val contacts = context.contentResolver.query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null,
                null,
                null,
                null
            )
//            listTemp.clear()
            listContact.clear()
            while (contacts!!.moveToNext()) {
                val name =
                    contacts.getString(contacts.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
                val numberPhone =
                    contacts.getString(contacts.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))

                listContact.add(DataContact(name,numberPhone))
            }
            contacts.close()
        }
        catch (e : Exception){
            Log.e("Error in three: ",e.toString())
            Toast.makeText(context,e.message,Toast.LENGTH_LONG).show()
        }
    }
}
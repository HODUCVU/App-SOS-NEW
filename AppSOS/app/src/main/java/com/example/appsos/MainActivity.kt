package com.example.appsos

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.appsos.databinding.ActivityMainBinding
import com.example.appsos.getDataSOS.LocationOfMap
import com.example.appsos.getDataSOS.ReadContact

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    private lateinit var location : LocationOfMap

    private lateinit var readContact: ReadContact
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
        location()
        readContact = ReadContact(this)
        readContact.readContact()
        isPermission()
    }

    private fun location() {
        location = LocationOfMap(this)
        location.init()
    }
    private fun isPermission() {
        try {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.SEND_SMS, Manifest.permission.READ_SMS, Manifest.permission.RECEIVE_SMS),
                PackageManager.PERMISSION_GRANTED
            )
        }
        catch (e : Exception) {
            Toast.makeText(this,"Error", Toast.LENGTH_LONG).show()
        }
    }
    override fun onRestart() {
        super.onRestart()
        location.init()
        readContact.readContact()
        isPermission()
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == 123) {
            if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.e("onPermissionResult: ","onRequestPermissionsResult")
            }
        }
        else if(requestCode == 111 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            readContact.queryListContact()
        }
        else  if (requestCode == 100 && grantResults.isNotEmpty() && (grantResults[0] + grantResults[1]
                    == PackageManager.PERMISSION_GRANTED)) {
            location.getCurrentLocation()
        }
    }
}
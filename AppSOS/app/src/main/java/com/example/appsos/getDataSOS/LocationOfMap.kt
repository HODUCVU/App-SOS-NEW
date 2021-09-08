package com.example.appsos.getDataSOS

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.LocationManager
import android.os.Looper
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.appsos.MainActivity
import com.example.appsos.dataObject.address
import com.example.appsos.dataObject.kinhdo
import com.example.appsos.dataObject.vido
import com.google.android.gms.location.*
import java.io.IOException
import java.util.*


class LocationOfMap(private val context : MainActivity) {

    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var locationRequest : LocationRequest
//    fun initOfMap() {
//        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)
//    }
    fun init() {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(
           context
        )
        //show dialog cho phép vi tri va tin nhan
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(context,Manifest.permission.ACCESS_COARSE_LOCATION)== PackageManager.PERMISSION_GRANTED
            && ContextCompat.checkSelfPermission(context, Manifest.permission.SEND_SMS)== PackageManager.PERMISSION_GRANTED){
            getCurrentLocation()
        }else {
            ActivityCompat.requestPermissions(context
                , arrayOf(Manifest.permission.ACCESS_FINE_LOCATION
                    ,Manifest.permission.ACCESS_COARSE_LOCATION
                    ,Manifest.permission.SEND_SMS)
                ,100)
        }
    }

    fun getCurrentLocation() {
        val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
            || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            if (ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return
            }
            fusedLocationProviderClient.lastLocation.addOnCompleteListener { task ->
                val location = task.result
                if (location != null) {
                    vido = location.latitude
                    kinhdo = location.longitude
                    try{
                        val geocoder = Geocoder(context, Locale.getDefault())
                        val locationLatLog = geocoder.getFromLocation(vido, kinhdo,1)
                        address.clear()
                        address.add("Tôi Đang Gặp Nạn Tại: ")
                        val locationName = locationLatLog[0].getAddressLine(0)
                        address.add(locationName + "\n")
                        address.add("Vĩ Độ: $vido\n")
                        address.add("Kinh Độ: $kinhdo")
                    }catch (e : IOException) {
                        Log.e("in 74 LocationOfMap: ",e.toString())
                        Toast.makeText(context,e.message,Toast.LENGTH_SHORT).show()
                    }
                }else {
                    locationRequest = LocationRequest()
                        .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                        .setInterval(10000)
                        .setFastestInterval(1000)
                        .setNumUpdates(1)
                    val locationCallback = object : LocationCallback() {
                         override fun onLocationResult(p0: LocationResult) {
                             val lastLocation = p0.lastLocation
                             vido = lastLocation.latitude
                             kinhdo = lastLocation.longitude
                         //set new Location
                             try {
                                 val geocoder = Geocoder(context, Locale.getDefault())
                                 val locationLatLog = geocoder.getFromLocation(vido, kinhdo,1)
                                 address.clear()
                                 address.add("Tôi Đang Gặp Nạn Tại: ")
                                 val locationName = locationLatLog[0].getAddressLine(0)
                                 address.add(locationName + "\n")
                                 address.add("Vĩ Độ: $vido\n")
                                 address.add("Kinh Độ: $kinhdo")
                             }catch (e : IOException) {
                                 Log.e("in 103 LocationOfMap: ",e.toString())
                                 Toast.makeText(context,e.message,Toast.LENGTH_SHORT).show()
                             }
                         }
                    }
                    fusedLocationProviderClient.requestLocationUpdates(locationRequest
                        ,locationCallback, Looper.myLooper())
                }
            }
        }
        else {
            context.startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
        }
    }
//    private val locationCallback =
//        object : LocationCallback() {
//            override fun onLocationResult(p0: LocationResult) {
//                val lastLocation = p0.lastLocation
//                //set new Location
//                address = "Lat: ${lastLocation.latitude}, Long: ${lastLocation.longitude}\n" +
//                        "Location: ${getLocaleName(lastLocation.latitude,lastLocation.longitude)}\n"
//            }
//        }
    //check uses permission
//    private fun checkPermission() : Boolean {
//        if(
//            ActivityCompat.checkSelfPermission(
//                context, Manifest.permission.ACCESS_FINE_LOCATION)
//            == PackageManager.PERMISSION_GRANTED
//            && ActivityCompat.checkSelfPermission(
//                context,Manifest.permission.ACCESS_COARSE_LOCATION)
//            == PackageManager.PERMISSION_GRANTED
//        )
//            return true
//        return false
//    }
//
//    //get uses permission
//    private fun requestPermission() {
//        ActivityCompat.requestPermissions(
//            context,
//            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION,
//                Manifest.permission.ACCESS_COARSE_LOCATION),
//            //context.PLACE_PICKER_REQUEST
//            123
//        )
//    }
//    //check Location service pf device enable
//    private fun isLocationEnable() : Boolean {
//        val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
//        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
//                || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
//    }
//    //
//    fun getLastLocation() {
//        try {
//            //check permission
//            if (checkPermission()) {
//                //check location service enable
//                if (isLocationEnable()) {
//                    fusedLocationProviderClient.lastLocation.addOnCompleteListener { task ->
//                        val location = task.result
//                        if (location == null) {
//                            //if location == null then will get the new user's location
//                            getNewLocation()
//                        } else {
//                           address =
//                                "Lat: ${location.latitude}, Long: ${location.longitude}\n" +
//                                        "Location: ${getLocaleName(location.latitude,location.longitude)}\n"
//
//                        }
//                    }
//                } else {
//                    Toast.makeText(
//                        context,
//                        "You ever haven't allow enable your Location service",
//                        Toast.LENGTH_LONG
//                    ).show()
//                }
//            } else {
//                requestPermission()
//            }
//        }catch (e : Exception) {
//            Log.e("in getLastLocation: ",e.toString())
//            Toast.makeText(context,e.message, Toast.LENGTH_LONG).show()
//        }
//    }
//    private fun getNewLocation() {
//        try {
//            locationRequest = LocationRequest()
//            locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
//            locationRequest.interval = 0
//            locationRequest.fastestInterval = 0
//            locationRequest.numUpdates = 2
//            //
//            if (ActivityCompat.checkSelfPermission(
//                    context,
//                    Manifest.permission.ACCESS_FINE_LOCATION
//                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
//                    context,
//                    Manifest.permission.ACCESS_COARSE_LOCATION
//                ) != PackageManager.PERMISSION_GRANTED
//            ) {
//                return
//            }
//            //
//            fusedLocationProviderClient.requestLocationUpdates(
//                locationRequest, locationCallback, Looper.myLooper()
//                //create val locationCallback
//            )
//        }
//    catch (e : Exception) {
//            Log.e("in getNewLocation: ",e.toString())
//            Toast.makeText(context,e.message, Toast.LENGTH_LONG).show()
//        }
//    }
//    private val locationCallback =
//        object : LocationCallback() {
//            override fun onLocationResult(p0: LocationResult) {
//                val lastLocation = p0.lastLocation
//                //set new Location
//                address = "Lat: ${lastLocation.latitude}, Long: ${lastLocation.longitude}\n" +
//                        "Location: ${getLocaleName(lastLocation.latitude,lastLocation.longitude)}\n"
//            }
//        }
//    //get location
//    private fun getLocaleName(lat : Double, long : Double) : String{
//        val address : String
//        val geoCoder = Geocoder(context, Locale.getDefault())
//        val location = geoCoder.getFromLocation(lat, long,1)
//        address = location[0].getAddressLine(0)
//        return address
//    }
}
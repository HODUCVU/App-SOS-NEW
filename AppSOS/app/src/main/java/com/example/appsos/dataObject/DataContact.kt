package com.example.appsos.dataObject

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.appsos.R

data class DataContact(val name : String, val phoneNumber : String)

data class DataTutorial(@DrawableRes val image : Int, @StringRes val title : Int,@StringRes val tutorial : Int)

val listContact = mutableListOf<DataContact>()
val listMessage = mutableListOf<DataContact>()

val listTutorial = mutableListOf(
    DataTutorial(R.drawable.outputonlinegiftools,R.string.SOS,R.string.tutorialSOS),
    DataTutorial(R.drawable.themsdt,R.string.addNumber,R.string.tutorialAddNumber),
    DataTutorial(R.drawable.ic_location,R.string.location,R.string.tutorialGoogleMap),
    DataTutorial(R.drawable.socuu,R.string.aid,R.string.tutorialAid),
    DataTutorial(R.drawable.goibao,R.string.callPhone,R.string.tutorialCall),
    DataTutorial(R.drawable.kinang,R.string.skill,R.string.tutorialSkill)
)

val listCall = listOf(DataContact("Tư vấn và hỗ trợ trẻ em","111"),
    DataContact("Trợ giúp và tìm kiếm cứu nạn","112"),
    DataContact("Gọi công an","113"),
    DataContact("Gọi cứu hỏa","114"),
    DataContact("Gọi cứu thương","115")
)
var check = false
val address = arrayListOf<String>()
var vido : Double = 0.0
var kinhdo = 0.0
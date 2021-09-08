package com.example.appsos.fragmentOfLearn

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.appsos.adapter.AdapterCall
import com.example.appsos.dataObject.listCall
import com.example.appsos.databinding.FragmentCallPhoneBinding


class CallPhone : Fragment() {

    private var _binding : FragmentCallPhoneBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapterCallPhone: AdapterCall

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCallPhoneBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycleCallPhone()
    }
    private fun recycleCallPhone() {
        adapterCallPhone = AdapterCall(this, listCall)
        binding.recycleCall.adapter = adapterCallPhone
        binding.recycleCall.setHasFixedSize(true)
        adapterCallPhone.notifyDataSetChanged()
    }
    fun intentCallPhone(position : Int) {
        val numberPhone = listCall[position].phoneNumber
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:$numberPhone")
        startActivity(intent)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
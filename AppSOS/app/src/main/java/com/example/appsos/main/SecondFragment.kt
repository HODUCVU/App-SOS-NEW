package com.example.appsos.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.appsos.R
import com.example.appsos.dataObject.kinhdo
import com.example.appsos.dataObject.vido
import com.example.appsos.databinding.FragmentSecondBinding

class SecondFragment : Fragment(),View.OnClickListener {

    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Glide.with(this).load(R.drawable.xuong).into(binding.imgSwipeDown)
        binding.apply {
            onClickThis()
        }
    }
    private fun onClickThis() {
        binding.apply {
            imgSwipeDown.setOnClickListener(this@SecondFragment)
            btnAddNumberPhone.setOnClickListener(this@SecondFragment)
            btnAid.setOnClickListener(this@SecondFragment)
            btnCallPhone.setOnClickListener(this@SecondFragment)
            btnSkill.setOnClickListener(this@SecondFragment)
            btnTutorial.setOnClickListener(this@SecondFragment)
            btnLocation.setOnClickListener(this@SecondFragment)
        }
    }
    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.img_swipe_down -> {
                findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
                com.example.appsos.dataObject.check = true
            }
            R.id.btnAddNumberPhone -> {
                findNavController().navigate(R.id.action_SecondFragment_to_addNumberPhone)
            }
            R.id.btnAid -> {
                findNavController().navigate(R.id.action_SecondFragment_to_aid)
            }
            R.id.btnCallPhone -> {
                findNavController().navigate(R.id.action_SecondFragment_to_callPhone)
            }
            R.id.btnTutorial -> {
                findNavController().navigate(R.id.action_SecondFragment_to_tutorial)
            }
            R.id.btnSkill -> {
                findNavController().navigate(R.id.action_SecondFragment_to_skill)
            }
            R.id.btnLocation -> {
                onGoogleMap()
            }
        }
    }
    private fun onGoogleMap() {
        val gmmIntentUri = Uri.parse("geo:$vido,$kinhdo?z=100000") //geo:latitude,longitude?z=zoom
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")
        mapIntent.resolveActivity(requireContext().packageManager)?.let {
            startActivity(mapIntent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
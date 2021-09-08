package com.example.appsos.fragmentOfLearn

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.appsos.adapter.AdapterTutorial
import com.example.appsos.dataObject.listTutorial
import com.example.appsos.databinding.FragmentTutorialBinding


class Tutorial : Fragment() {
    private var _binding : FragmentTutorialBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapterTutorial: AdapterTutorial
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTutorialBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            adapterTutorial = AdapterTutorial(listTutorial)
            recycleTutorial.adapter = adapterTutorial
            recycleTutorial.setHasFixedSize(true)
            adapterTutorial.notifyDataSetChanged()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
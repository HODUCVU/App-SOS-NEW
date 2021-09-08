package com.example.appsos.Dialog

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.appsos.R
import com.example.appsos.dataObject.address
import com.example.appsos.dataObject.listMessage
import com.example.appsos.databinding.FragmentCheckCancelSendBinding
import com.example.appsos.getDataSOS.sms

class CheckCancelSend : Fragment() {

    private var _binding : FragmentCheckCancelSendBinding? = null
    private val binding get() = _binding!!
    private lateinit var sms : sms
    val listImage = listOf(R.drawable.img3,R.drawable.img2,R.drawable.img1)
    var position = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCheckCancelSendBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            sms = sms()
            val countDownTimer = object : CountDownTimer(4000, 1000) {
                override fun onTick(l: Long) {
                    imageCountDown.setImageResource(listImage[position])
                    position++
                }
                override fun onFinish() {
                        sms.init(
                            address,
                            listMessage, context
                        )
                        findNavController().navigate(R.id.action_checkCancelSend_to_FirstFragment)

                }
            }.start()
            btnCancelSend.setOnClickListener {
                countDownTimer.cancel()
                findNavController().navigate(R.id.action_checkCancelSend_to_FirstFragment)
                Toast.makeText(context,"Đã Hủy Tin Khẩn Cấp",Toast.LENGTH_SHORT).show()
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
package com.example.appsos.main

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.appsos.Dialog.DialogAddNumberPhone
import com.example.appsos.R
import com.example.appsos.dataObject.MyDatabase
import com.example.appsos.dataObject.address
import com.example.appsos.dataObject.listMessage
import com.example.appsos.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    private lateinit var swipeUpLinear : LinearLayout
    private lateinit var dialog : DialogAddNumberPhone
    //mục đích khai báo db là để query listMessage ngay lần đầu tiên vào app
    private lateinit var db : MyDatabase
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        db = MyDatabase(requireContext())
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        swipeUpLinear = view.findViewById(R.id.swipe_up)
        init()
        sOS()
        showLocation()
        binding.apply {
            binding.imgSwipeUp.setOnClickListener {
                reInit()
                val move = AnimationUtils.loadAnimation(context, R.anim.keolen)
                swipeUpLinear.startAnimation(move)
                val countDownTimer = object : CountDownTimer(1000, 10) {
                    override fun onTick(l: Long) {}
                    override fun onFinish() {
                        findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
                    }
                }.start()
                com.example.appsos.dataObject.check = false
            }
            if(com.example.appsos.dataObject.check) {
                reInit()
                val move = AnimationUtils.loadAnimation(context, R.anim.keoxuong)
                swipeUpLinear.startAnimation(move)
                val countDownTimer = object : CountDownTimer(1000, 1000) {
                    override fun onTick(l: Long) {}
                    override fun onFinish() {
                        init()
                    }
                }.start()
                com.example.appsos.dataObject.check = false
            }
        }
    }
    private fun sOS() {
        binding.btnSOS.setOnClickListener {
            if(listMessage.isNotEmpty())
               findNavController().navigate(R.id.action_FirstFragment_to_checkCancelSend)
            else {
                dialog = DialogAddNumberPhone("Danh Sách Nhận Tin KHẨN CẤP Rỗng, Vui Lòng Vào Mục Thêm SĐT Để Tạo Danh Sách Nhận Tin KHẨN CẤP")
                dialog.show(requireFragmentManager(),"123")
            }

        }
    }

    override fun onResume() {
        super.onResume()
        showLocation()
    }
    private fun showLocation() {
        if(address.isEmpty())
            binding.txtShow.text = getString(R.string.noConnectInternet)
        else {
            //Loại "Tôi Đang Gặp Nạn: "
                binding.txtShow.text = ""
            for (i in 1 until address.size)
                binding.txtShow.append(address[i])
        }
    }
    private fun init() {
        db.query()
        Glide.with(this).load(R.drawable.len).into(binding.imgSwipeUp)
        binding.imgSwipeUp.visibility = View.VISIBLE
        swipeUpLinear.visibility = View.INVISIBLE
    }
    private fun reInit(){
        binding.imgSwipeUp.visibility = View.INVISIBLE
        swipeUpLinear.visibility = View.VISIBLE
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
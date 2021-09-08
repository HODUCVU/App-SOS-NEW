package com.example.appsos.fragmentOfLearn

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.appsos.Dialog.DialogAddNumberPhone
import com.example.appsos.adapter.Adapter
import com.example.appsos.adapter.AdapterListMessage
import com.example.appsos.dataObject.MyDatabase
import com.example.appsos.dataObject.listContact
import com.example.appsos.dataObject.listMessage
import com.example.appsos.databinding.FragmentAddNumberPhoneBinding


class AddNumberPhone : Fragment() {

    lateinit var db : MyDatabase

    private lateinit var dialog : DialogAddNumberPhone

    private var _binding : FragmentAddNumberPhoneBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapterListContact : Adapter
    private lateinit var adapterListMessage : AdapterListMessage
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        db = MyDatabase(requireContext())
        _binding = FragmentAddNumberPhoneBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            recycleListContact()
            recycleListMessage()
        }
    }
    private fun recycleListContact() {
        adapterListContact = Adapter(this,listContact)
        binding.recycleListContact.adapter = adapterListContact
        binding.recycleListContact.setHasFixedSize(true)
        adapterListContact.notifyDataSetChanged()
    }
    fun recycleListMessage() {
        db.query()
        adapterListMessage = AdapterListMessage(this,listMessage)
        binding.recycleListMessage.adapter = adapterListMessage
        binding.recycleListMessage.setHasFixedSize(true)
        adapterListMessage.notifyDataSetChanged()
    }
    fun dialogAdded(message : String) {
        dialog = DialogAddNumberPhone(message)
        dialog.show(requireFragmentManager(),"123")
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
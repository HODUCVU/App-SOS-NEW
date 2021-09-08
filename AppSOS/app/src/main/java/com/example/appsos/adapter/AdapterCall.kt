package com.example.appsos.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appsos.R
import com.example.appsos.dataObject.DataContact
import com.example.appsos.fragmentOfLearn.CallPhone

class AdapterCall(private val context : CallPhone,
                  private val dataSet: List<DataContact>)
    : RecyclerView.Adapter<AdapterCall.ViewHolderItem>() {

    inner class ViewHolderItem(view: View) : RecyclerView.ViewHolder(view){
        val name: TextView = view.findViewById(R.id.txtName)
        val numberPhone: TextView = view.findViewById(R.id.txtNumberPhone)
        val call : Button  = view.findViewById(R.id.btnCall)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderItem {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_call, null)
        return ViewHolderItem(view)
    }

    override fun onBindViewHolder(holder: ViewHolderItem, position: Int) {
        val item = dataSet[position]
        holder.name.text = item.name
        holder.numberPhone.text = item.phoneNumber
        holder.call.setOnClickListener {
            context.intentCallPhone(position)
        }
    }
    override fun getItemCount() : Int = dataSet.size
}
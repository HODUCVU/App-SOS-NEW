package com.example.appsos.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appsos.R
import com.example.appsos.dataObject.DataContact
import com.example.appsos.fragmentOfLearn.AddNumberPhone

class AdapterListMessage(private val context : AddNumberPhone,
                         private val dataSet: List<DataContact>)
    : RecyclerView.Adapter<AdapterListMessage.ViewHolderItem>() {
    inner class ViewHolderItem(view: View) : RecyclerView.ViewHolder(view){
        val name: TextView = view.findViewById(R.id.txtName)
        val numberPhone: TextView = view.findViewById(R.id.txtNumberPhone)
        val eraseNumberPhone : Button  = view.findViewById(R.id.btnErase)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderItem {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_message, null)
        return ViewHolderItem(view)
    }

    override fun onBindViewHolder(holder: ViewHolderItem, position: Int) {
        val item = dataSet[position]
        holder.name.text = item.name
        holder.numberPhone.text = item.phoneNumber
        holder.eraseNumberPhone.setOnClickListener {
            context.dialogAdded("Đã Xóa ${dataSet[position].name} Từ Danh Sách")
            context.db.delete(dataSet[position])
            context.db.query()
            notifyDataSetChanged()
        }
    }

    override fun getItemCount() : Int = dataSet.size
}
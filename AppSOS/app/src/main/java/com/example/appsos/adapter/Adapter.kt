package com.example.appsos.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appsos.R
import com.example.appsos.dataObject.DataContact
import com.example.appsos.dataObject.listMessage
import com.example.appsos.fragmentOfLearn.AddNumberPhone

class Adapter(private val context : AddNumberPhone,
              private val dataSet: List<DataContact>)
    : RecyclerView.Adapter<Adapter.ViewHolderItem>() {

    inner class ViewHolderItem(view: View) : RecyclerView.ViewHolder(view){
        val name: TextView = view.findViewById(R.id.txtName)
        val numberPhone: TextView = view.findViewById(R.id.txtNumberPhone)
        val addNumberPhone : Button  = view.findViewById(R.id.btnAdd)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderItem {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, null)
        return ViewHolderItem(view)
    }

    override fun onBindViewHolder(holder: ViewHolderItem, position: Int) {
        val item = dataSet[position]
        holder.name.text = item.name
        holder.numberPhone.text = item.phoneNumber
        holder.addNumberPhone.setOnClickListener {
            if(listMessage.contains(dataSet[position]))
            {
                val message = "Số Này Đã Được Thêm"
                context.dialogAdded(message)
            }else {
                context.db.insert(dataSet[position])
                context.db.query()
                notifyDataSetChanged()
                context.recycleListMessage()
                context.dialogAdded("Đã Thêm ${dataSet[position].name} Vào Danh Sách")
            }
        }
    }
    override fun getItemCount() : Int = dataSet.size
}
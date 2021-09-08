package com.example.appsos.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appsos.R
import com.example.appsos.dataObject.DataTutorial

class AdapterTutorial(private val dataSet: List<DataTutorial>)
    : RecyclerView.Adapter<AdapterTutorial.ViewHolderItem>() {

    inner class ViewHolderItem(view: View) : RecyclerView.ViewHolder(view){
        val image : ImageView = view.findViewById(R.id.imageTutorial)
        val txtTitle : TextView = view.findViewById(R.id.txtTutorialTitle)
        val txtTutorial : TextView = view.findViewById(R.id.txtTutorial)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderItem {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list__item_tutorial, null)
        return ViewHolderItem(view)
    }

    override fun onBindViewHolder(holder: ViewHolderItem, position: Int) {
        val item = dataSet[position]
        holder.image.setImageResource(item.image)
        holder.txtTitle.setText(item.title)
        holder.txtTutorial.setText(item.tutorial)
    }
    override fun getItemCount() : Int = dataSet.size
}
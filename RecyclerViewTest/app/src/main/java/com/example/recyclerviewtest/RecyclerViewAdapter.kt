package com.example.recyclerviewtest

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_layout.view.*

class RecyclerViewAdapter( var dataList: List<String>, private val context: Context) :
    RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {

        companion object{
        const val TAG:String="RecyclerViewAdapter"
    }


//     inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        var textView: TextView = itemView.display_tv
//    }

    class MyViewHolder:RecyclerView.ViewHolder{
        constructor(itemView:View):super(itemView)

    }
    override fun getItemViewType(position: Int):Int{
        Log.e(TAG, "getItemViewType: $position" )
        return 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        Log.e(TAG, "onCreateViewHolder: ")
        return when (viewType) {
            1 -> MyViewHolder(
                LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false)
            )
            else -> {
                MyViewHolder(
                    LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false)
                )
            }
        }

    }

    override fun getItemCount(): Int {
        Log.e(TAG, "getItemCout:" )

        return dataList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Log.e(TAG, "onBindViewHolder: ")
        holder.textView.text = dataList[position]
        holder.itemView.setOnClickListener {
            Toast.makeText(context, "${holder.textView.text}", Toast.LENGTH_LONG).show()
        }
    }
}


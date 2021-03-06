package com.example.comment

import android.view.LayoutInflater
import com.example.commentdemo.R



import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.commentdemo.model.CommentModel

class CommentAdapter(var context: Context, var data: ArrayList<CommentModel>) : BaseAdapter() {


    override fun getView(position: Int,  convertView: View?, parent: ViewGroup?): View {
        val holder:CommentViewHolder
        var view:View
        if(convertView==null){
            holder=CommentViewHolder()
            view=LayoutInflater.from(context).inflate(R.layout.item_comment_layout,null)
            holder.commentName=view.findViewById(R.id.commenter_tv)
            holder.commentContent=view.findViewById(R.id.comment_content_tv)
            view.tag=holder
        }else{
            view=convertView
            holder= convertView.tag as CommentViewHolder
        }

        holder.commentName?.text = data[position].name
        holder.commentContent?.text=data[position].content

        return view
    }

    override fun getItem(position: Int): Any {
        return data[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return data.size
    }

    open inner class CommentViewHolder {
        var commentName: TextView? = null
        var commentContent: TextView? = null
    }

    fun addComment(comment: CommentModel) {
        data.add(0,comment)
        notifyDataSetChanged()
    }
}
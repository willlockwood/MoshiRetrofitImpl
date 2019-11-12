package com.willlockwood.moshiretrofitexample

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_row.view.*

class BaseRecyclerAdapter internal constructor(
    context: Context
): RecyclerView.Adapter<BaseRecyclerAdapter.BaseViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var objects = emptyList<BaseObject>()
    private var listener: AdapterView.OnItemClickListener? = null

    inner class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val username: TextView = itemView.name
        val url: TextView = itemView.url
        val likes: TextView = itemView.likes
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val itemView = inflater.inflate(R.layout.item_row, parent, false)
        return BaseViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val item = objects[position]
        holder.apply {
            username.text = item.user
            likes.text = item.likes.toString()
            url.text = item.url
//            this.itemView.setOnClickListener {  }
        }
//        holder.username.text = item.user
//        holder.likes.text = item.likes.toString()
//        holder.url.text = item.largeImageUrl
    }

    fun setObjects(objects: List<BaseObject>) {
        this.objects = objects
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return objects.size
    }
}
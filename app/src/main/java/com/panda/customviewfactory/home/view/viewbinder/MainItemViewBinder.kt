package com.panda.customviewfactory.home.view.viewbinder

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.panda.customviewfactory.R
import com.panda.customviewfactory.home.model.MainItemModel
import com.panda.customviewfactory.home.view.adapter.MainAdapter
import me.drakeet.multitype.ItemViewBinder

/**
 * @ClassName MainItemViewBinder
 * @Description TODO
 * @Author dongchengpu
 * @Date 2021/1/29 下午6:13
 * @Version 1.0
 */
class MainItemViewBinder(private val callback: MainAdapter.ItemClickCallback) :
    ItemViewBinder<MainItemModel, MainItemViewBinder.MainItemViewHolder>() {

    override fun onCreateViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): MainItemViewHolder {
        return MainItemViewHolder(inflater.inflate(R.layout.main_holder_item_layout, parent, false))
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onBindViewHolder(holder: MainItemViewHolder, item: MainItemModel) {
        if (item.title == "ClockView"){
            Glide.with(holder.itemView.context).asGif().load(item.iconRes).into(holder.icon)
            holder.icon.setImageDrawable(holder.itemView.context.getDrawable(item.iconRes))
        }else{
            holder.icon.setImageDrawable(holder.itemView.context.getDrawable(item.iconRes))
        }
        holder.desc.text = item.title
        holder.itemView.setOnClickListener { callback.onItemClicked(item) }
    }

    class MainItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val icon: ImageView = view.findViewById(R.id.icon_res)
        val desc: TextView = view.findViewById(R.id.desc)
    }
}
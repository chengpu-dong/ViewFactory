package com.panda.customviewfactory.home.view.viewbinder

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.panda.customviewfactory.R
import com.panda.customviewfactory.home.model.MainItemModel
import me.drakeet.multitype.ItemViewBinder

/**
 * @ClassName MainItemViewBinder
 * @Description TODO
 * @Author dongchengpu
 * @Date 2021/1/29 下午6:13
 * @Version 1.0
 */
class MainItemViewBinder : ItemViewBinder<MainItemModel, MainItemViewBinder.MainItemViewHolder>() {

    override fun onCreateViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): MainItemViewHolder {
        return MainItemViewHolder(inflater.inflate(R.layout.main_holder_item_layout, parent,false))
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onBindViewHolder(holder: MainItemViewHolder, item: MainItemModel) {
        holder.icon.setImageDrawable(holder.itemView.context.getDrawable(item.iconRes))
        holder.desc.text = item.title
    }

    class MainItemViewHolder : RecyclerView.ViewHolder {
        val icon: ImageView
        val desc: TextView

        constructor(view: View) : super(view) {
            icon = view.findViewById(R.id.icon_res)
            desc = view.findViewById(R.id.desc)
        }
    }
}
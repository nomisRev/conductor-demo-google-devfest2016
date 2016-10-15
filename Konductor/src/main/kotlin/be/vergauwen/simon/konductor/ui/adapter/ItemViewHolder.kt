package be.vergauwen.simon.konductor.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import be.vergauwen.simon.konductor.R
import be.vergauwen.simon.common.di.model.Item
import be.vergauwen.simon.common.ui.widget.ItemIconView

class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val icon: ItemIconView
    val text: TextView

    init {
        text = itemView.findViewById(R.id.action_text) as TextView
        icon = itemView.findViewById(R.id.action_icon) as ItemIconView
    }

    fun bind(item: Item) {
        text.text = item.name
        icon.icon = item.getIcon(itemView.context)
        icon.setColorId(item.itemColorId)
    }
}
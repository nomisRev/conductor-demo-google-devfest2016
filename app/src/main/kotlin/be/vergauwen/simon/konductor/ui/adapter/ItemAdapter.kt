package be.vergauwen.simon.konductor.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import be.vergauwen.simon.konductor.R
import be.vergauwen.simon.konductor.core.anko.itemIconView
import be.vergauwen.simon.konductor.core.anko.setMinimumListHeight
import be.vergauwen.simon.konductor.core.anko.setSelectableItemBackground
import be.vergauwen.simon.konductor.core.model.Item
import org.jetbrains.anko.*

class ItemAdapter(private val items: MutableList<Item> = mutableListOf<Item>()) : RecyclerView.Adapter<ItemViewHolder>() {

    ///////////////////
    // Adapter Impl

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder = ItemViewHolder(buildListItemUI(parent.context))

    override fun onBindViewHolder(holder: ItemViewHolder?, position: Int) {
        holder?.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    ///////////////////

    fun addItem(item: Item) {
        if (items.contains(item)) return
        items.add(item)
        notifyDataSetChanged()
    }

    fun getItem(position: Int): Item = items[position]

    ///////////////////
    // Anko View

    private fun buildListItemUI(context: Context): View =
            context.UI {
                linearLayout {
                    lparams(width = matchParent) {
                        padding = dimen(R.dimen.activity_horizontal_margin)
                    }
                    setSelectableItemBackground()
                    setMinimumListHeight()

                    itemIconView {
                        id = R.id.action_icon
                        gravity = Gravity.CENTER
                    }.lparams(width = 0, height = wrapContent, weight = 1f) {
                        marginEnd = dip(16f)
                    }

                    textView {
                        id = R.id.action_text
                        gravity = Gravity.START
                    }.lparams(width = 0, height = wrapContent, weight = 4f)
                }
            }.view
}
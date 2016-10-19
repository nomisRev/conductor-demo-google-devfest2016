package be.vergauwen.simon.konductor.ui.controllers

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import be.vergauwen.simon.common.di.anko.widthProcent
import be.vergauwen.simon.common.ui.layout.LayoutInjector
import be.vergauwen.simon.common.ui.widget.util.onItemClick
import be.vergauwen.simon.konductor.ui.adapter.ItemAdapter
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class MasterViewLayout : LayoutInjector<MasterViewController> {
    override fun injectLayout(detailView: MasterViewController): View {
        return detailView.activity.UI {
            detailView.background = linearLayout {
                configuration(orientation = Orientation.PORTRAIT) {
                    detailView.recyclerView = recyclerView {
                        init(detailView)
                    }.lparams(width = matchParent, height = matchParent)
                }

                configuration(orientation = Orientation.LANDSCAPE) {
                    detailView.recyclerView = recyclerView {
                        init(detailView)
                    }.lparams(width = widthProcent(50), height = matchParent)

                    detailView.detailContainer = frameLayout {

                    }.lparams(width = matchParent, height = matchParent)
                }
            }
        }.view
    }

    private fun RecyclerView.init(t: MasterViewController) {
        setHasFixedSize(true)
        layoutManager = LinearLayoutManager(context)
        t.itemAdapter = ItemAdapter()
        adapter = t.itemAdapter
        onItemClick { view, index -> t.onRowSelected(index) }
    }
}
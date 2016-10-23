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
    override fun injectLayout(masterView: MasterViewController): View {
        return masterView.activity.UI {
            masterView.background = linearLayout {
                configuration(orientation = Orientation.PORTRAIT) {
                    masterView.recyclerView = recyclerView {
                        init(masterView)
                    }.lparams(width = matchParent, height = matchParent)
                }

                configuration(orientation = Orientation.LANDSCAPE) {
                    masterView.recyclerView = recyclerView {
                        init(masterView)
                    }.lparams(width = matchParent, height = matchParent)
                }

                configuration(orientation = Orientation.LANDSCAPE, smallestWidth = 700) {
                    masterView.recyclerView = recyclerView {
                        init(masterView)
                    }.lparams(width = widthProcent(50), height = matchParent)

                    masterView.detailContainer = frameLayout {

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
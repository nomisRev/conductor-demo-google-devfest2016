package be.vergauwen.simon.conductor.ui.controllers

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import be.vergauwen.simon.common.ui.layout.ViewBinder
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class MasterViewLayout: ViewBinder<MasterViewControllerMVP> {

    override fun bind(masterView: MasterViewControllerMVP): View {
        return masterView.activity.UI {
            masterView.container = linearLayout {
                configuration(orientation = Orientation.PORTRAIT) {
                    masterView.recyclerView = recyclerView {
                        init()
                    }.lparams(width = matchParent, height = matchParent)
                }

                configuration(orientation = Orientation.LANDSCAPE) {
                    masterView.recyclerView = recyclerView {
                        init()
                    }.lparams(width = dip(275), height = matchParent)

                    masterView.detailContainer = frameLayout {

                    }.lparams(width = matchParent, height = matchParent)
                }
            }
        }.view
    }

    override fun unbind(masterView: MasterViewControllerMVP) {
        masterView.container = null
        masterView.recyclerView = null
        masterView.detailContainer = null
    }

    private fun RecyclerView.init() {
        setHasFixedSize(true)
        layoutManager = LinearLayoutManager(context)
    }
}
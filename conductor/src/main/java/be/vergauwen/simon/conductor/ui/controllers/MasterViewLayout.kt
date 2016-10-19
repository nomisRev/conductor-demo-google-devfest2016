package be.vergauwen.simon.conductor.ui.controllers

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import be.vergauwen.simon.common.di.anko.widthProcent
import be.vergauwen.simon.common.ui.layout.LayoutInjector
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class MasterViewLayout : LayoutInjector<MasterViewControllerMVP> {
    override fun injectLayout(detailView: MasterViewControllerMVP): View {
        return detailView.activity.UI {
            detailView.container = linearLayout {
                configuration(orientation = Orientation.PORTRAIT) {
                    detailView.recyclerView = recyclerView {
                        init()
                    }.lparams(width = matchParent, height = matchParent)
                }

                configuration(orientation = Orientation.LANDSCAPE) {
                    detailView.recyclerView = recyclerView {
                        init()
                    }.lparams(width = widthProcent(50), height = matchParent)

                    detailView.detailContainer = frameLayout {

                    }.lparams(width = matchParent, height = matchParent)
                }
            }
        }.view
    }

    private fun RecyclerView.init() {
        setHasFixedSize(true)
        layoutManager = LinearLayoutManager(context)
    }
}
package be.vergauwen.simon.conductor.ui.controllers

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class MasterViewLayout : ViewBinder<MasterViewControllerMVP> {

    override fun MasterViewControllerMVP.bind(): View {
        return activity.UI {
            container = linearLayout {
                configuration(orientation = Orientation.PORTRAIT) {
                    recyclerView = recyclerView {
                        init()
                    }.lparams(width = matchParent, height = matchParent)
                }

                configuration(orientation = Orientation.LANDSCAPE) {
                    recyclerView = recyclerView {
                        init()
                    }.lparams(width = dip(275), height = matchParent)

                    detailContainer = frameLayout {

                    }.lparams(width = matchParent, height = matchParent)
                }
            }
        }.view
    }

    override fun MasterViewControllerMVP.unbind() {
        container = null
        recyclerView = null
        detailContainer = null
    }

    private fun RecyclerView.init() {
        setHasFixedSize(true)
        layoutManager = LinearLayoutManager(context)
    }
}
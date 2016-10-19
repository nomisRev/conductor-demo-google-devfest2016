package be.vergauwen.simon.conductor.ui.controllers

import android.view.Gravity
import android.view.View
import be.vergauwen.simon.common.di.anko.itemIconView
import be.vergauwen.simon.common.ui.layout.LayoutInjector
import be.vergauwen.simon.conductor.R
import org.jetbrains.anko.*


class DetailViewLayout : LayoutInjector<DetailViewController> {
    override fun injectLayout(detailView: DetailViewController): View {
        return detailView.activity.UI {
            detailView.background = verticalLayout {
                lparams(width = matchParent, height = matchParent) {
                    padding = dimen(R.dimen.activity_horizontal_margin)
                }

                detailView.itemIconView = itemIconView {
                    gravity = Gravity.CENTER
                }.lparams(width = wrapContent, height = 0, weight = 1f) {
                    marginEnd = dip(16f)
                }

                detailView.actionText = textView {
                    gravity = Gravity.START
                }.lparams(width = wrapContent, height = 0, weight = 4f)
            }
        }.view
    }
}
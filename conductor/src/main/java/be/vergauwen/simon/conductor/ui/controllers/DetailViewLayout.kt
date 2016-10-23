package be.vergauwen.simon.conductor.ui.controllers

import android.view.Gravity
import android.view.View
import be.vergauwen.simon.common.di.anko.itemIconView
import be.vergauwen.simon.conductor.R
import org.jetbrains.anko.*


class DetailViewLayout : ViewBinder<DetailViewController> {

    override fun DetailViewController.bind(): View {
        return activity.UI {
            background = verticalLayout {
                lparams(width = matchParent, height = matchParent) {
                    padding = dimen(R.dimen.activity_horizontal_margin)
                }

                itemIconView = itemIconView {
                    gravity = Gravity.CENTER
                }.lparams(width = wrapContent, height = 0, weight = 1f) {
                    marginEnd = dip(16f)
                }

                actionText = textView {
                    gravity = Gravity.START
                }.lparams(width = wrapContent, height = 0, weight = 4f)
            }
        }.view
    }

    override fun DetailViewController.unbind() {
        background = null
        itemIconView = null
        actionText = null
    }
}
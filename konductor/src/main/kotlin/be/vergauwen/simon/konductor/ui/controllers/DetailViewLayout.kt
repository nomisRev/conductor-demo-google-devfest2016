package be.vergauwen.simon.konductor.ui.controllers

import android.view.Gravity
import android.view.View
import be.vergauwen.simon.common.di.anko.color
import be.vergauwen.simon.common.di.anko.drawable
import be.vergauwen.simon.common.di.anko.itemIconView
import be.vergauwen.simon.common.ui.layout.LayoutInjector
import be.vergauwen.simon.konductor.R
import be.vergauwen.simon.konductor.ui.controllers.DetailViewController.Companion.KEY_COLOR
import be.vergauwen.simon.konductor.ui.controllers.DetailViewController.Companion.KEY_DRAW_RES
import be.vergauwen.simon.konductor.ui.controllers.DetailViewController.Companion.KEY_TITLE
import org.jetbrains.anko.*

class DetailViewLayout : LayoutInjector<DetailViewController> {
    override fun injectLayout(detailView: DetailViewController): View {
        return detailView.activity.UI {
          verticalLayout {
              backgroundColor = color(detailView.args.getInt(KEY_COLOR))

              lparams(width = matchParent, height = matchParent) {
                    padding = dimen(R.dimen.activity_horizontal_margin)
                }

                itemIconView {
                    setColorId(detailView.args.getInt(KEY_COLOR))
                    icon = drawable(detailView.args.getInt(KEY_DRAW_RES))
                    gravity = Gravity.CENTER
                }.lparams(width = wrapContent, height = 0, weight = 1f) {
                    marginEnd = dip(16f)
                }

                textView {
                    text = detailView.args.getString(KEY_TITLE)
                    gravity = Gravity.START
                }.lparams(width = wrapContent, height = 0, weight = 4f)
            }
        }.view
    }
}
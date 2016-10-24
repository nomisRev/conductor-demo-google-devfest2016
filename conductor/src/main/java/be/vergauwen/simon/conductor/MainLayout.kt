package be.vergauwen.simon.conductor

import android.os.Build
import android.support.design.widget.AppBarLayout
import android.view.View
import be.vergauwen.simon.common.di.anko.actionBarSize
import be.vergauwen.simon.common.di.anko.changeHandlerFrameLayout
import be.vergauwen.simon.common.di.anko.color
import be.vergauwen.simon.common.ui.layout.ViewBinder
import org.jetbrains.anko.UI
import org.jetbrains.anko.appcompat.v7.toolbar
import org.jetbrains.anko.design.appBarLayout
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.matchParent

class MainLayout : ViewBinder<MainActivity> {
    override fun bind(mainView: MainActivity): View {
        return mainView.UI {
            coordinatorLayout {
                fitsSystemWindows = true

                appBarLayout {
                    toolbar {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) elevation = 4f
                        setTitleTextColor(color(android.R.color.white))
                        mainView.setSupportActionBar(this)
                    }.lparams(width = matchParent, height = actionBarSize())
                }.lparams(width = matchParent)

                mainView.container = changeHandlerFrameLayout {
                }.lparams(width = matchParent, height = matchParent) {
                    behavior = AppBarLayout.ScrollingViewBehavior()
                }
            }
        }.view
    }

    override fun unbind(mainView: MainActivity) {
        mainView.container = null
    }
}
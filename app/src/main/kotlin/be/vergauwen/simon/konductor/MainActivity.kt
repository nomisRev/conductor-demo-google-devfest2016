package be.vergauwen.simon.konductor

import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.ViewGroup
import be.vergauwen.simon.konductor.anko.actionBarSize
import be.vergauwen.simon.konductor.anko.changeHandlerFrameLayout
import com.bluelinelabs.conductor.Conductor
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.demo.ActionBarProvider
import com.bluelinelabs.conductor.demo.controllers.HomeController
import org.jetbrains.anko.UI
import org.jetbrains.anko.appcompat.v7.toolbar
import org.jetbrains.anko.design.appBarLayout
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.matchParent

class MainActivity : AppCompatActivity(), ActionBarProvider {

    internal var toolbar: Toolbar? = null
    internal var container: ViewGroup? = null

    private var router: Router? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        UI {
            coordinatorLayout {
                fitsSystemWindows = true
                appBarLayout {
                    toolbar = toolbar {

                    }.lparams(width = matchParent, height = actionBarSize())
                }.lparams(width = matchParent)

                container = changeHandlerFrameLayout {

                }.lparams(width = matchParent, height = matchParent) {
                    behavior = AppBarLayout.ScrollingViewBehavior()
                }
            }
        }

        setSupportActionBar(toolbar)

        container?.let { router = Conductor.attachRouter(this, it, savedInstanceState) }
        if (router?.hasRootController() == false) {
            router?.setRoot(RouterTransaction.with(HomeController()))
        }
    }

    override fun onBackPressed() {
        if (router?.handleBack() == false) {
            super.onBackPressed()
        }
    }

}
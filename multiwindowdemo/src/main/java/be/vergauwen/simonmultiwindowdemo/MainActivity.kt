package be.vergauwen.simonmultiwindowdemo

import android.os.Build
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.ViewGroup
import be.vergauwen.simon.common.di.component.ActivityComponent
import be.vergauwen.simon.common.di.component.DaggerActivityComponent
import be.vergauwen.simon.common.di.modules.ActivityModule
import com.bluelinelabs.conductor.Conductor
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import org.jetbrains.anko.appcompat.v7.toolbar
import org.jetbrains.anko.design.appBarLayout
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.matchParent

class MainActivity : AppCompatActivity() {

    private var toolBar: Toolbar? = null
    private var container: ViewGroup? = null

    private var router: Router? = null

    var component: ActivityComponent = DaggerActivityComponent.builder().activityModule(ActivityModule(this)).build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        coordinatorLayout {
            fitsSystemWindows = true

            appBarLayout {
                toolBar = toolbar {
                    popupTheme = R.style.AppTheme_PopupOverlay
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) elevation = 4f
                    setTitleTextColor(getColor(android.R.color.white))
                }.lparams(width = matchParent, height = actionBarSize())
            }.lparams(width = matchParent)

            container = changeHandlerFrameLayout {
                router = Conductor.attachRouter(this@MainActivity, this, savedInstanceState)
            }.lparams(width = matchParent, height = matchParent) {
                behavior = AppBarLayout.ScrollingViewBehavior()
            }
        }

        setSupportActionBar(toolBar)

        if (router?.hasRootController() == false) {
            router?.setRoot(RouterTransaction.with(CameraViewController()))
        }
    }

    override fun onBackPressed() {
        if (router?.handleBack() == false) {
            super.onBackPressed()
        }
    }
}
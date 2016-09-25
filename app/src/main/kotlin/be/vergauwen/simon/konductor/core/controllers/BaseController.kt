package be.vergauwen.simon.konductor.core.controllers

import android.os.Bundle
import android.support.v7.app.ActionBar
import android.view.View
import be.vergauwen.simon.konductor.KonductorApp
import com.bluelinelabs.conductor.ControllerChangeHandler
import com.bluelinelabs.conductor.ControllerChangeType
import com.bluelinelabs.conductor.demo.ActionBarProvider
import com.bluelinelabs.conductor.rxlifecycle.RxController

abstract class BaseController @JvmOverloads constructor(args: Bundle? = null) : RxController(args) {

    private var hasExited: Boolean = false
    abstract val title: String?

    // Note: This is just a quick demo of how an ActionBar *can* be accessed, not necessarily how it *should*
    // be accessed. In a production app, this would use Dagger instead.
    protected fun getActionBar(): ActionBar? {
        val actionBarProvider = activity as ActionBarProvider
        return actionBarProvider.supportActionBar
    }

    override fun onAttach(view: View) {
        getActionBar()?.title = title
        super.onAttach(view)
    }

    public override fun onDestroy() {
        super.onDestroy()
        if (hasExited) (activity.application as KonductorApp).refWatcher?.watch(this)
    }

    override fun onChangeEnded(changeHandler: ControllerChangeHandler, changeType: ControllerChangeType) {
        super.onChangeEnded(changeHandler, changeType)
        hasExited = !changeType.isEnter
        if (isDestroyed) (activity.application as KonductorApp).refWatcher?.watch(this)
    }
}
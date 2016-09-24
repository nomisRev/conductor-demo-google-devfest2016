package be.vergauwen.simon.konductor.controllers

import android.os.Bundle
import android.support.v7.app.ActionBar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.ControllerChangeHandler
import com.bluelinelabs.conductor.ControllerChangeType
import com.bluelinelabs.conductor.demo.ActionBarProvider
import com.bluelinelabs.conductor.demo.DemoApplication
import com.bluelinelabs.conductor.rxlifecycle.RxController

abstract class BaseController @JvmOverloads constructor(args: Bundle? = null) : RxController(args) {

    private var hasExited: Boolean = false
    abstract val title: String

    protected abstract fun inflateView(inflater: LayoutInflater, container: ViewGroup): View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View = inflateView(inflater, container)

    // Note: This is just a quick demo of how an ActionBar *can* be accessed, not necessarily how it *should*
    // be accessed. In a production app, this would use Dagger instead.
    protected fun getActionBar(): ActionBar? {
        val actionBarProvider = activity as ActionBarProvider
        return actionBarProvider.supportActionBar
    }

    override fun onAttach(view: View) {
        setTitle()
        super.onAttach(view)
    }

    protected fun setTitle() {
        var parentController: Controller? = parentController

        while (parentController != null) {
            if (parentController is BaseController) {
                return
            }
            parentController = parentController.parentController
        }

        getActionBar()?.title = title
    }

    //abstract fun getTitle(): String

    public override fun onDestroy() {
        super.onDestroy()

        if (hasExited) {
            DemoApplication.refWatcher.watch(this)
        }
    }

    override fun onChangeEnded(changeHandler: ControllerChangeHandler, changeType: ControllerChangeType) {
        super.onChangeEnded(changeHandler, changeType)

        hasExited = !changeType.isEnter
        if (isDestroyed) {
            DemoApplication.refWatcher.watch(this)
        }
    }
}
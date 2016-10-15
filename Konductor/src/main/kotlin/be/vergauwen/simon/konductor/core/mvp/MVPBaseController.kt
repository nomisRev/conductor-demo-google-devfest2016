package be.vergauwen.simon.konductor.core.mvp

import android.os.Bundle
import android.support.v7.app.ActionBar
import android.view.View
import be.vergauwen.simon.common.mvp.MVPContract
import be.vergauwen.simon.konductor.KonductorApp
import com.bluelinelabs.conductor.ControllerChangeHandler
import com.bluelinelabs.conductor.ControllerChangeType
import com.bluelinelabs.conductor.rxlifecycle.RxController
import icepick.Icepick

abstract class MVPBaseController<V : MVPContract.View, out P : MVPContract.Presenter<V>,
        out C : MVPContract.Component<V, P>> @JvmOverloads constructor(args: Bundle? = null) : RxController(args) {

    private var hasExited: Boolean = false
    protected abstract val component: C
    protected val supportActionBar: ActionBar? by lazy { component.actionBarProvider.supportActionBar }
    protected val presenter: P by lazy { component.presenter }
    protected open val title: String? = null

//    // Note: This is just a quick demo of how an ActionBar *can* be accessed, not necessarily how it *should*
//    // be accessed. In a production app, this would use Dagger instead.
//    protected fun getActionBar(): ActionBar? {
//        val actionBarProvider = activity as ActionBarProvider
//        return actionBarProvider.supportActionBar
//    }

    @Suppress("UNCHECKED_CAST")
    override fun onAttach(view: View) {
        super.onAttach(view)
        title?.let { supportActionBar?.title = it }
        presenter.attachView(this as V)
    }

    override fun onDetach(view: View) {
        super.onDetach(view)
        presenter.detachView()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Icepick.saveInstanceState(this, outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Icepick.restoreInstanceState(this, savedInstanceState)
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
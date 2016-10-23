package be.vergauwen.simon.konductor.ui.controllers

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import be.vergauwen.simon.common.di.changehandlers.CircularRevealChangeHandlerCompat
import be.vergauwen.simon.common.di.model.Item
import be.vergauwen.simon.common.di.modules.DataModule
import be.vergauwen.simon.common.ui.component.DaggerMasterComponent
import be.vergauwen.simon.common.ui.component.MasterComponent
import be.vergauwen.simon.common.ui.contract.MasterContract
import be.vergauwen.simon.common.ui.presenter.MasterPresenter
import be.vergauwen.simon.konductor.MainActivity
import be.vergauwen.simon.konductor.R
import be.vergauwen.simon.konductor.core.mvp.MVPBaseController
import be.vergauwen.simon.konductor.ui.adapter.ItemAdapter
import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.ControllerChangeHandler
import com.bluelinelabs.conductor.RouterTransaction

class MasterViewController : MVPBaseController<MasterContract.View, MasterPresenter, MasterComponent>(), MasterContract.View {

    override val title: String?
        get() = "Konductor"

    internal var recyclerView: RecyclerView? = null
    internal var detailContainer: ViewGroup? = null
    internal var background: View? = null
    internal var itemAdapter = ItemAdapter()
    private val layoutInject = MasterViewLayout()

    override val component: MasterComponent
        get() = DaggerMasterComponent.builder().activityComponent((activity as MainActivity).component).dataModule(DataModule()).build()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View = layoutInject.injectLayout(this)

    override fun onAttach(view: View) {
        super.onAttach(view)
        presenter.getData()
    }

    internal fun onRowSelected(index: Int) {
        val item = itemAdapter.getItem(index)
        if (item != null) {
            showDetail(DetailViewController(item.name, item.itemColorId, item.drawableResId), getCircularRevealHandler(index))
        }
    }

    private fun getCircularRevealHandler(index: Int): ControllerChangeHandler {
        val circularRevealView = recyclerView?.getChildAt(index)?.findViewById(R.id.action_icon)
        return if (circularRevealView != null && background != null) CircularRevealChangeHandlerCompat(circularRevealView, background!!) else CircularRevealChangeHandlerCompat()
    }

    private fun showDetail(controller: Controller, controllerChangeHandler: ControllerChangeHandler) {
        if (detailContainer != null) {
            getChildRouter(detailContainer!!, null).setRoot(RouterTransaction.with(controller))
        } else {
            router.pushController(RouterTransaction.with(controller).pushChangeHandler(controllerChangeHandler).popChangeHandler(controllerChangeHandler))
        }
    }

    //MasterView impl
    override fun addItem(item: Item) = itemAdapter.addItem(item)
}
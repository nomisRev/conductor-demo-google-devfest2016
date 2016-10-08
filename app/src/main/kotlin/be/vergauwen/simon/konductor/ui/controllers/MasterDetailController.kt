package be.vergauwen.simon.konductor.ui.controllers

import android.content.res.Configuration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import be.vergauwen.simon.konductor.MainActivity
import be.vergauwen.simon.konductor.core.anko.widthProcent
import be.vergauwen.simon.konductor.core.di.modules.DataModule
import be.vergauwen.simon.konductor.core.model.Item
import be.vergauwen.simon.konductor.core.mvp.MVPBaseController
import be.vergauwen.simon.konductor.ui.adapter.ItemAdapter
import be.vergauwen.simon.konductor.ui.component.DaggerMasterComponent
import be.vergauwen.simon.konductor.ui.component.MasterComponent
import be.vergauwen.simon.konductor.ui.contract.MasterContract
import be.vergauwen.simon.konductor.ui.presenter.MasterPresenter
import be.vergauwen.simon.konductor.ui.widget.util.onItemClick
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.changehandler.HorizontalChangeHandler
import icepick.State
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class MasterDetailController : MVPBaseController<MasterContract.View, MasterPresenter, MasterComponent>(), MasterContract.View {

    override val title: String?
        get() = "Konductor"

    private var recyclerView: RecyclerView? = null
    private var detailContainer: ViewGroup? = null
    private var itemAdapter = ItemAdapter()

    @State private var selectedIndex: Int = -1

    override val component: MasterComponent
        get() = DaggerMasterComponent.builder().activityComponent((activity as MainActivity).component).dataModule(DataModule()).build()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View = activity.UI {
        linearLayout {
            configuration(orientation = Orientation.PORTRAIT) {
                recyclerView = recyclerView {
                    init()
                }.lparams(width = matchParent, height = matchParent)
            }

            configuration(orientation = Orientation.LANDSCAPE) {
                recyclerView = recyclerView {
                    init()
                }.lparams(width = widthProcent(40), height = matchParent)

                detailContainer = frameLayout {

                }.lparams(width = matchParent, height = matchParent)
                onRowSelected(selectedIndex)
            }
        }
    }.view

    private fun RecyclerView.init() {
        setHasFixedSize(true)
        layoutManager = LinearLayoutManager(context)
        itemAdapter = ItemAdapter()
        adapter = itemAdapter
        presenter.getData()
        onItemClick { view, index -> onRowSelected(index) }
    }

    //View item selected
    private fun onRowSelected(index: Int) {
        if (index == -1) return

        selectedIndex = index

        val model = itemAdapter.getItem(index)
        val controller = DetailController(model.name, model.itemColorId, model.drawableResId)

        if (activity.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            detailContainer?.let { getChildRouter(it, null).setRoot(RouterTransaction.with(controller)) }
        } else {
            router.pushController(RouterTransaction.with(controller).pushChangeHandler(HorizontalChangeHandler()).popChangeHandler(HorizontalChangeHandler()))
        }
    }

    //View impl
    override fun addItem(item: Item) = itemAdapter.addItem(item)
}
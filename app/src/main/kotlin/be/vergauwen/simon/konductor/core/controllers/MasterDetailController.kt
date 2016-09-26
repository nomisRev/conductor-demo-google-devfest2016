package be.vergauwen.simon.konductor.core.controllers

import android.content.res.Configuration
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import be.vergauwen.simon.konductor.core.RxDataRepo
import be.vergauwen.simon.konductor.ui.adapter.ItemAdapter
import be.vergauwen.simon.konductor.ui.widget.util.onItemClick
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.changehandler.HorizontalChangeHandler
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import timber.log.Timber

class MasterDetailController : BaseController() {

    override val title: String?
        get() = "Konductor Master-Detail"


    private var recyclerView: RecyclerView? = null
    private var detailContainer: ViewGroup? = null
    private var itemAdapter = ItemAdapter()
    private var selectedIndex: Int = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {

        return activity.UI {
            linearLayout {
                recyclerView = recyclerView {
                    setHasFixedSize(true)
                    layoutManager = LinearLayoutManager(context)
                    adapter = itemAdapter
                    onItemClick { view, index -> onRowSelected(index) }
                }.lparams(width = matchParent, height = matchParent)

                configuration(orientation = Orientation.LANDSCAPE) {
                    recyclerView.apply {
                        lparams(width = dip(200))
                    }

                    detailContainer = frameLayout {

                    }.lparams(width = matchParent, height = matchParent)
                    onRowSelected(selectedIndex)
                }
            }
        }.view
    }


    override fun onAttach(view: View) {
        super.onAttach(view)
        RxDataRepo.getData().subscribe { itemAdapter.addItem(item = it) }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Timber.e("onSaveInstanceState($outState)")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Timber.e("onRestoreInstanceState($savedInstanceState)")
    }

    //View item selected
    internal fun onRowSelected(index: Int) {
        selectedIndex = index

        val model = itemAdapter.getItem(index)
        val controller = DetailController(model.name, model.itemColorId, model.drawableResId)

        if (activity.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            detailContainer?.let { getChildRouter(it, null).setRoot(RouterTransaction.with(controller)) }
        } else {
            router.pushController(RouterTransaction.with(controller).pushChangeHandler(HorizontalChangeHandler()).popChangeHandler(HorizontalChangeHandler()))
        }
    }
}
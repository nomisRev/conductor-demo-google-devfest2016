package be.vergauwen.simon.konductor.core.controllers

import android.content.res.Configuration
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import be.vergauwen.simon.konductor.core.RxDataRepo
import be.vergauwen.simon.konductor.core.anko.widthProcent
import be.vergauwen.simon.konductor.ui.adapter.ItemAdapter
import be.vergauwen.simon.konductor.ui.widget.util.onItemClick
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.changehandler.HorizontalChangeHandler
import icepick.Icepick
import icepick.State
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import timber.log.Timber

class MasterDetailController : BaseController() {

    override val title: String?
        get() = "Konductor Master-Detail"


    private var recyclerView: RecyclerView? = null
    private var detailContainer: ViewGroup? = null
    private var itemAdapter = ItemAdapter()
    @State private var selectedIndex: Int = -1

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        Timber.e("onCreateView()")
        return activity.UI {
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
    }

    private fun RecyclerView.init() {
        setHasFixedSize(true)
        layoutManager = LinearLayoutManager(context)
        RxDataRepo.getData().toList().subscribe { item -> itemAdapter = ItemAdapter(item) }
        adapter = itemAdapter
        onItemClick { view, index -> onRowSelected(index) }
    }

    override fun onDestroyView(view: View?) {
        Timber.e("onDestroyView(): $view")
        super.onDestroyView(view)
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.e("onDestroy()")
    }

    override fun onAttach(view: View) {
        super.onAttach(view)
        Timber.e("onAttach($view)")
    }

    override fun onDetach(view: View) {
        super.onDetach(view)
        Timber.e("onDetach(): $view")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Icepick.saveInstanceState(this, outState)
        Timber.e("onSaveInstanceState($outState)")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Icepick.restoreInstanceState(this, savedInstanceState)
        Timber.e("onRestoreInstanceState($savedInstanceState)")
    }

    //View item selected
    internal fun onRowSelected(index: Int) {
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
}
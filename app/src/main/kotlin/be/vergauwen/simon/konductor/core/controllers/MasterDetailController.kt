package be.vergauwen.simon.konductor.core.controllers

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import be.vergauwen.simon.konductor.core.RxDataRepo
import be.vergauwen.simon.konductor.ui.adapter.ItemAdapter
import org.jetbrains.anko.UI
import org.jetbrains.anko.linearLayout
import org.jetbrains.anko.matchParent
import org.jetbrains.anko.recyclerview.v7.recyclerView
import timber.log.Timber

class MasterDetailController : BaseController() {

    override val title: String?
        get() = "Konductor Master-Detail"


    private var recyclerView: RecyclerView? = null
    private var detailContainer: FrameLayout? = null
    private var itemAdapter = ItemAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {

        return activity.UI {
            linearLayout {
                recyclerView = recyclerView {
                    setHasFixedSize(true)
                    layoutManager = LinearLayoutManager(context)
                    adapter = itemAdapter
                }.lparams(width = matchParent, height = matchParent)

//                configuration(orientation = Orientation.LANDSCAPE) {
//                    recyclerView.apply {
//                        lparams(width = dip(200))
//                    }
//
//                    detailContainer = frameLayout {
//
//                    }.lparams(width = matchParent, height = matchParent)
//                }
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
//        selectedIndex = index
//
//        val model = MasterDetailListController.DetailItemModel.values()[index]
//        val controller = ChildController(model.detail, model.backgroundColor, true)

//        router.pushController(RouterTransaction.with(controller).pushChangeHandler(HorizontalChangeHandler()).popChangeHandler(HorizontalChangeHandler()))
    }
}
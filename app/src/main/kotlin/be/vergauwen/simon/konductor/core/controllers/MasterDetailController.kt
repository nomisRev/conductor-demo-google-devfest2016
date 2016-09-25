package be.vergauwen.simon.konductor.core.controllers

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.jetbrains.anko.UI
import org.jetbrains.anko.configuration
import org.jetbrains.anko.recyclerview.v7.recyclerView
import timber.log.Timber

class MasterDetailController : BaseController() {

    override val title: String?
        get() = "Konductor Master-Detail"


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View =
            activity.UI {
                recyclerView {
                    setHasFixedSize(true)
                    layoutManager = LinearLayoutManager(context)

                }
                configuration {
                    
                }
            }.view


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
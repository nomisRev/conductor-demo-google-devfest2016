package be.vergauwen.simon.konductor.ui.controllers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import be.vergauwen.simon.common.ui.component.DaggerDetailComponent
import be.vergauwen.simon.common.ui.component.DetailComponent
import be.vergauwen.simon.common.ui.contract.DetailContract
import be.vergauwen.simon.common.ui.presenter.DetailPresenter
import be.vergauwen.simon.konductor.MainActivity
import be.vergauwen.simon.konductor.core.mvp.MVPBaseController

class DetailViewController(args: Bundle) : MVPBaseController<DetailContract.View, DetailPresenter, DetailComponent>(args), DetailContract.View {

    companion object {
        val KEY_TITLE = "ChildController.title"
        val KEY_COLOR = "ChildController.color"
        val KEY_DRAW_RES = "ChildController.drawableResId"
    }

    private val viewBinder = DetailViewLayout()
    override val component: DetailComponent
        get() = DaggerDetailComponent.builder().activityComponent((activity as MainActivity).component).build()

    constructor(title: String, iconColorResId: Int, drawableResId: Int) : this((Bundle().apply {
        putString(KEY_TITLE, title)
        putInt(KEY_COLOR, iconColorResId)
        putInt(KEY_DRAW_RES, drawableResId)
    }))

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View = viewBinder.bind(this)
}
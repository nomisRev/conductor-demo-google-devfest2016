package be.vergauwen.simon.konductor.core.controllers

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import be.vergauwen.simon.konductor.R
import be.vergauwen.simon.konductor.core.anko.itemIconView
import be.vergauwen.simon.konductor.ui.widget.ItemIconView
import org.jetbrains.anko.*

class DetailController(args: Bundle) : BaseController(args) {

    private var text: TextView? = null
    private var icon: ItemIconView? = null

    override val title: String?
        get() = "Detail controller"

    companion object {
        val KEY_TITLE = "ChildController.title"
        val KEY_COLOR = "ChildController.color"
        val KEY_DRAW_RES = "ChildController.drawableResId"
    }

    constructor(title: String, iconColorResId: Int, drawableResId: Int) : this((Bundle().apply {
        putString(KEY_TITLE, title)
        putInt(KEY_COLOR, iconColorResId)
        putInt(KEY_DRAW_RES, drawableResId)
    }))

    init {
        text?.text = args.getString(KEY_TITLE)
        icon?.setColorId(args.getInt(KEY_COLOR))
        icon?.icon = ContextCompat.getDrawable(activity, args.getInt(KEY_DRAW_RES))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View = activity.UI {
        verticalLayout {
            lparams(width = matchParent, height = matchParent) {
                padding = dimen(R.dimen.activity_horizontal_margin)
            }

            icon = itemIconView {
                id = R.id.action_icon
                gravity = Gravity.CENTER
            }.lparams(width = 0, height = wrapContent, weight = 1f) {
                marginEnd = dip(16f)
            }

            text = textView {
                id = R.id.action_text
                gravity = Gravity.START
            }.lparams(width = 0, height = wrapContent, weight = 4f)
        }
    }.view
}
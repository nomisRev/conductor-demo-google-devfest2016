package be.vergauwen.simon.konductor.core.controllers

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import be.vergauwen.simon.konductor.R
import be.vergauwen.simon.konductor.core.anko.itemIconView
import org.jetbrains.anko.*

class DetailController(args: Bundle) : BaseController(args) {

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


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View = activity.UI {
        verticalLayout {
            backgroundColor = ContextCompat.getColor(activity, args.getInt(KEY_COLOR))
            lparams(width = matchParent, height = matchParent) {
                padding = dimen(R.dimen.activity_horizontal_margin)
            }

            itemIconView {
                id = R.id.action_icon
                setColorId(args.getInt(KEY_COLOR))
                icon = ContextCompat.getDrawable(activity, args.getInt(KEY_DRAW_RES))
                gravity = Gravity.CENTER
            }.lparams(width = wrapContent, height = 0, weight = 1f) {
                marginEnd = dip(16f)
            }

            textView {
                id = R.id.action_text
                text = args.getString(KEY_TITLE)
                gravity = Gravity.START
            }.lparams(width = wrapContent, height = 0, weight = 4f)
        }
    }.view
}
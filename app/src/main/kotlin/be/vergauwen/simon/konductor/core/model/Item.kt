package be.vergauwen.simon.konductor.core.model

import android.content.Context
import android.graphics.drawable.Drawable

interface Item {
    var name : String
    val itemColorId : Int
    fun getIcon(context: Context) : Drawable
}
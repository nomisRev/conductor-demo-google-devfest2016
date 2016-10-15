package be.vergauwen.simon.common.di.model

import android.content.Context
import android.graphics.drawable.Drawable

interface Item {
    var name : String
    val itemColorId : Int
    val drawableResId: Int
    fun getIcon(context: Context) : Drawable
}
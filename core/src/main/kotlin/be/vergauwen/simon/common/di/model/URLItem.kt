package be.vergauwen.simon.common.di.model

import android.content.Context
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import be.vergauwen.simon.common.R

class URLItem(url: String) : Item {

    override  var name: String = url

    override val itemColorId: Int
        get() = R.color.url

    override val drawableResId: Int
        get() = R.drawable.ic_url

    override fun getIcon(context: Context): Drawable = ContextCompat.getDrawable(context, R.drawable.ic_url)
}
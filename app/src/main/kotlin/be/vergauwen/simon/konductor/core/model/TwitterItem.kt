package be.vergauwen.simon.konductor.core.model

import android.content.Context
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import be.vergauwen.simon.konductor.R

class TwitterItem(twitter: String) : Item {

    override var name: String = twitter

    override val itemColorId: Int
        get() = R.color.twitter

    override val drawableResId: Int
        get() = R.drawable.ic_twitter

    override fun getIcon(context: Context): Drawable = ContextCompat.getDrawable(context, R.drawable.ic_twitter)
}
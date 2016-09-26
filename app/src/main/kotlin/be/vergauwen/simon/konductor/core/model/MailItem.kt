package be.vergauwen.simon.konductor.core.model

import android.content.Context
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import be.vergauwen.simon.konductor.R

class MailItem(mail: String) : Item {

    override var name: String = mail

    override val itemColorId: Int
        get() = R.color.email

    override fun getIcon(context: Context): Drawable = ContextCompat.getDrawable(context, R.drawable.ic_email)
}
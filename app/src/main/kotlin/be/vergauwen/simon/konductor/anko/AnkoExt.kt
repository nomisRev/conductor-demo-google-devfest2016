package be.vergauwen.simon.konductor.anko

import android.content.Intent
import android.graphics.drawable.Drawable
import android.support.annotation.ColorRes
import android.support.annotation.DrawableRes
import android.support.v4.content.ContextCompat
import android.util.TypedValue
import android.view.View
import android.view.ViewManager
import com.bluelinelabs.conductor.ChangeHandlerFrameLayout
import com.bluelinelabs.conductor.demo.R
import org.jetbrains.anko.custom.ankoView

fun ViewManager.changeHandlerFrameLayout(theme: Int = 0) = changeHandlerFrameLayout(theme) {}
inline fun ViewManager.changeHandlerFrameLayout(theme: Int = 0, init: ChangeHandlerFrameLayout.() -> Unit) = ankoView(::ChangeHandlerFrameLayout, theme, init)

fun View.drawable(@DrawableRes resource: Int) : Drawable = ContextCompat.getDrawable(context, resource)

fun View.color(@ColorRes resource: Int) : Int = ContextCompat.getColor(context, resource)

fun Intent.defaultCategory(): Intent = apply { addCategory(Intent.CATEGORY_DEFAULT) }

fun View.actionBarSize(): Int {
    val tv = TypedValue()
    if (context.theme.resolveAttribute(R.attr.actionBarSize, tv, true)) {
        return TypedValue.complexToDimensionPixelSize(tv.data, resources.displayMetrics)
    }
    return 0
}
package be.vergauwen.simon.common.di.anko

import android.R
import android.content.Context
import android.content.Intent
import android.graphics.Point
import android.graphics.drawable.Drawable
import android.support.annotation.ColorRes
import android.support.annotation.DrawableRes
import android.support.v4.content.ContextCompat
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.view.ViewManager
import android.view.WindowManager
import be.vergauwen.simon.common.ui.widget.ItemIconView
import com.bluelinelabs.conductor.ChangeHandlerFrameLayout
import org.jetbrains.anko.custom.ankoView


fun ViewManager.changeHandlerFrameLayout(theme: Int = 0) = changeHandlerFrameLayout(theme) {}
inline fun ViewManager.changeHandlerFrameLayout(theme: Int = 0, init: ChangeHandlerFrameLayout.() -> Unit) = ankoView(::ChangeHandlerFrameLayout, theme, init)

fun ViewManager.itemIconView(theme: Int = 0) = itemIconView(theme) {}
inline fun ViewManager.itemIconView(theme: Int = 0, init: ItemIconView.() -> Unit) = ankoView({ ItemIconView(it) }, theme, init)

fun View.drawable(@DrawableRes resource: Int): Drawable = ContextCompat.getDrawable(context, resource)

fun View.color(@ColorRes resource: Int): Int = ContextCompat.getColor(context, resource)

fun Intent.defaultCategory(): Intent = apply { addCategory(Intent.CATEGORY_DEFAULT) }

fun View.actionBarSize(): Int {
    val tv = TypedValue()
    if (context.theme.resolveAttribute(R.attr.actionBarSize, tv, true)) {
        return TypedValue.complexToDimensionPixelSize(tv.data, resources.displayMetrics)
    }
    return 0
}

fun ViewGroup.setSelectableItemBackground() {
    val typedArray = context.obtainStyledAttributes(intArrayOf(R.attr.selectableItemBackground))
    setBackgroundResource(typedArray.getResourceId(0, 0))
    typedArray.recycle()
    isClickable = true
}

fun ViewGroup.setMinimumListHeight() {
    val tv = TypedValue()
    if (context.theme.resolveAttribute(android.R.attr.listPreferredItemHeight, tv, true)) {
        minimumHeight = TypedValue.complexToDimensionPixelSize(tv.data, resources.displayMetrics)
    }
}

fun <T : View> T.widthProcent(procent: Int): Int = getAppUseableScreenSize().x.toFloat().times(procent.toFloat() / 100).toInt()

fun <T : View> T.heightProcent(procent: Int): Int = getAppUseableScreenSize().y.toFloat().times(procent.toFloat() / 100).toInt()

fun <T : View> T.getAppUseableScreenSize(): Point {
    val display = (context.getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay
    val size = Point()
    display.getSize(size)
    return size
}
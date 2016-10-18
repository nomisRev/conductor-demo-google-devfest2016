package be.vergauwen.simonmultiwindowdemo

import android.support.annotation.ColorRes
import android.support.v4.content.ContextCompat
import android.util.TypedValue
import android.view.View
import android.view.ViewManager
import com.bluelinelabs.conductor.ChangeHandlerFrameLayout
import com.google.android.cameraview.CameraView
import org.jetbrains.anko.custom.ankoView

fun ViewManager.changeHandlerFrameLayout(theme: Int = 0) = changeHandlerFrameLayout(theme) {}
inline fun ViewManager.changeHandlerFrameLayout(theme: Int = 0, init: ChangeHandlerFrameLayout.() -> Unit) = ankoView(::ChangeHandlerFrameLayout, theme, init)

fun ViewManager.cameraView(theme: Int = 0) = cameraView(theme) {}
inline fun ViewManager.cameraView(theme: Int = 0, init: CameraView.() -> Unit) = ankoView(::CameraView, theme, init)

fun View.color(@ColorRes resource: Int): Int = ContextCompat.getColor(context, resource)

fun View.actionBarSize(): Int {
    val tv = TypedValue()
    if (context.theme.resolveAttribute(R.attr.actionBarSize, tv, true)) {
        return TypedValue.complexToDimensionPixelSize(tv.data, resources.displayMetrics)
    }
    return 0
}

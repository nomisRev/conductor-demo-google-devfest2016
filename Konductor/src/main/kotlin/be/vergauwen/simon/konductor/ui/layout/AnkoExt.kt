package be.vergauwen.simon.konductor.ui.layout

import android.content.Context
import android.util.DisplayMetrics
import org.jetbrains.anko.windowManager

data class ScreenSize(val width: Float, val height: Float)

fun Context.screenSize(): ScreenSize {
    val dm = DisplayMetrics().apply { this@screenSize.windowManager.defaultDisplay.getMetrics(this) }
    return ScreenSize(dm.widthPixels.toFloat() / dm.densityDpi, dm.heightPixels.toFloat() / dm.densityDpi)
}
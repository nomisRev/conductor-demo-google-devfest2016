package be.vergauwen.simonmultiwindowdemo

import android.view.ViewManager
import com.google.android.cameraview.CameraView
import org.jetbrains.anko.custom.ankoView

fun ViewManager.cameraView(theme: Int = 0) = cameraView(theme) {}
inline fun ViewManager.cameraView(theme: Int = 0, init: CameraView.() -> Unit) = ankoView(::CameraView, theme, init)
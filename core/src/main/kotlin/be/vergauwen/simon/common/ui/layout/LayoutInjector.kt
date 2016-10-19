package be.vergauwen.simon.common.ui.layout

import android.view.View


interface LayoutInjector<in T> {
    fun injectLayout(t: T) : View
}
package be.vergauwen.simon.conductor.ui.controllers

import android.view.View


interface ViewBinder<in T> {
    fun T.bind() : View
    fun T.unbind()
}
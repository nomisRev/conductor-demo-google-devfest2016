package be.vergauwen.simon.common.ui.widget.util

import android.support.v7.widget.RecyclerView
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View

interface OnItemClickListener {
    fun onItemClick(view: View, position: Int)
}

fun RecyclerView.onItemClick(onItemClick: (View, Int) -> Unit) {
    addItemClickListener(object : OnItemClickListener {
        override fun onItemClick(view: View, position: Int) {
            onItemClick.invoke(view, position)
        }
    })
}

fun RecyclerView.addItemClickListener(listener: OnItemClickListener) {

    val gestureDetector = GestureDetector(context, object : GestureDetector.SimpleOnGestureListener() {
        override fun onSingleTapUp(e: MotionEvent): Boolean {
            return true
        }
    })


    this.addOnItemTouchListener(object : RecyclerView.OnItemTouchListener {
        override fun onTouchEvent(rv: RecyclerView?, e: MotionEvent?) {
        }

        override fun onInterceptTouchEvent(rv: RecyclerView?, e: MotionEvent): Boolean {
            val childView = this@addItemClickListener.findChildViewUnder(e.x, e.y)
            if (childView != null && gestureDetector.onTouchEvent(e)) {
                listener.onItemClick(childView, this@addItemClickListener.getChildAdapterPosition(childView))
            }
            return false
        }

        override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {
        }
    })
}
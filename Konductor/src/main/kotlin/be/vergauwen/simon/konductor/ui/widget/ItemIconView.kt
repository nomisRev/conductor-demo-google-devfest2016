package be.vergauwen.simon.konductor.ui.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.support.annotation.ColorRes
import android.util.AttributeSet
import android.view.View
import be.vergauwen.simon.konductor.R
import be.vergauwen.simon.konductor.core.anko.color
import org.jetbrains.anko.dip

/**
 * Simple circle view with drawable. Always 46dp.
 */
class ItemIconView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : View(context, attrs, defStyleAttr) {

    private val size = 46f

    var icon: Drawable? = null
        set(value) {
            field = value
            invalidate()
            requestLayout()
        }

    var color = color(R.color.colorAccent)
        set(value) {
            field = value
            paint.color = value
            invalidate()
            requestLayout()
        }

    private val paint: Paint

    init {
        paint = Paint().apply {
            isAntiAlias = true
            style = Paint.Style.FILL
            color = this@ItemIconView.color
        }

        getAttrs(attrs, defStyleAttr)
    }

    private fun getAttrs(attrs: AttributeSet?, defStyleAttr: Int) {
        val attributes = context.theme.obtainStyledAttributes(attrs, R.styleable.ItemIconView, defStyleAttr, 0)

        try {

        } finally {
            attributes.recycle()
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val calcWidth = dip(size).toInt() + paddingStart + paddingEnd
        val calcHeight = dip(size).toInt() + paddingTop + paddingBottom
        setMeasuredDimension(calcWidth, calcHeight)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawCircle(getCenterX(), getCenterY(), dip(size / 2).toFloat(), paint)
        if (icon != null) {
            icon?.bounds = getDrawableBounds()
            icon?.draw(canvas)
        }
    }

    fun setColorId(@ColorRes colorRes: Int) {
        color = color(colorRes)
    }

    private fun getCenterX(): Float = ((width - paddingStart - paddingEnd).toFloat() / 2) + paddingStart

    private fun getCenterY(): Float = ((height - paddingTop - paddingBottom).toFloat() / 2) + paddingTop

    private fun getDrawableBounds(): Rect {
        val side = (dip(24f))

        return Rect().apply {
            top = (getCenterY() - side / 2).toInt()
            left = (getCenterX() - side / 2).toInt()
            right = (getCenterX() + side / 2).toInt()
            bottom = (getCenterX() + side / 2).toInt()
        }
    }
}